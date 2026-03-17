package com.youwillwin.service;

import com.youwillwin.dto.QuestionRequest;
import com.youwillwin.dto.TestCaseRequest;
import com.youwillwin.exception.QuestionNotFoundException;
import com.youwillwin.model.Difficulty;
import com.youwillwin.model.Question;
import com.youwillwin.model.Tag;
import com.youwillwin.model.TestCase;
import com.youwillwin.repository.QuestionRepository;
import com.youwillwin.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final TagRepository tagRepository;

    public QuestionService(QuestionRepository questionRepository, TagRepository tagRepository) {
        this.questionRepository = questionRepository;
        this.tagRepository = tagRepository;
    }

    @Transactional(readOnly = true)
    public List<Question> findAll(Difficulty difficulty, String tagName) {
        List<Question> questions;
        if (difficulty == null && tagName == null) {
            questions = questionRepository.findAllWithTags();
        } else {
            questions = questionRepository.findByFilters(difficulty, tagName);
        }
        if (!questions.isEmpty()) {
            questionRepository.fetchTestCases(questions);
        }
        return questions;
    }

    @Transactional(readOnly = true)
    public Question findById(Long id) {
        Question question = questionRepository.findByIdWithTags(id)
            .orElseThrow(() -> new QuestionNotFoundException(id));
        questionRepository.findByIdWithTestCases(id);
        return question;
    }

    @Transactional
    public Question create(QuestionRequest request) {
        Question question = new Question();
        mapRequestToEntity(request, question);
        return questionRepository.save(question);
    }

    @Transactional
    public Question update(Long id, QuestionRequest request) {
        Question question = findById(id);
        question.getTestCases().clear();
        question.getTags().clear();
        mapRequestToEntity(request, question);
        return questionRepository.save(question);
    }

    @Transactional
    public void delete(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new QuestionNotFoundException(id);
        }
        questionRepository.deleteById(id);
    }

    private void mapRequestToEntity(QuestionRequest request, Question question) {
        question.setTitle(request.title());
        question.setDescription(request.description());
        question.setDifficulty(request.difficulty());
        question.setMethodSignature(request.methodSignature());
        question.setDriverCode(request.driverCode());

        if (request.tags() != null && !request.tags().isEmpty()) {
            Set<Tag> tags = tagRepository.findByNameIn(request.tags());
            question.setTags(tags);
        }

        if (request.testCases() != null) {
            for (TestCaseRequest tcReq : request.testCases()) {
                TestCase tc = new TestCase();
                tc.setInput(tcReq.input());
                tc.setExpectedOutput(tcReq.expectedOutput());
                tc.setSample(tcReq.sample());
                tc.setOrderIndex(tcReq.orderIndex());
                tc.setDisplayInput(tcReq.displayInput());
                tc.setDisplayOutput(tcReq.displayOutput());
                tc.setQuestion(question);
                question.getTestCases().add(tc);
            }
        }
    }
}
