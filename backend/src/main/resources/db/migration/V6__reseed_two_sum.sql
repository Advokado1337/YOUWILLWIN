-- Clear old data
DELETE FROM test_cases WHERE question_id = 1;
DELETE FROM question_tags WHERE question_id = 1;
DELETE FROM attempts WHERE question_id = 1;
DELETE FROM questions WHERE id = 1;

-- Re-insert Two Sum with driver code
INSERT INTO questions (id, title, description, difficulty, method_signature, driver_code) VALUES (
    1,
    'Two Sum',
    'Given an array of integers nums and an integer target, return the indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.',
    'EASY',
    '    public int[] twoSum(int[] nums, int target) {
        // your solution here
        return new int[]{};
    }',
    '    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        int target = sc.nextInt();
        int[] result = new Solution().twoSum(nums, target);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            if (i > 0) sb.append(" ");
            sb.append(result[i]);
        }
        System.out.println(sb.toString());
    }'
);

-- Re-insert tags
INSERT INTO question_tags (question_id, tag_id)
SELECT 1, id FROM tags WHERE name IN ('arrays', 'hashmap');

-- Re-insert test cases with display fields
INSERT INTO test_cases (question_id, input, expected_output, is_sample, order_index, display_input, display_output) VALUES
    (1, '4' || chr(10) || '2 7 11 15' || chr(10) || '9', '0 1', true, 0,
     'nums = [2,7,11,15], target = 9', '[0, 1]'),
    (1, '3' || chr(10) || '3 2 4' || chr(10) || '6', '1 2', true, 1,
     'nums = [3,2,4], target = 6', '[1, 2]'),
    (1, '2' || chr(10) || '3 3' || chr(10) || '6', '0 1', false, 2,
     'nums = [3,3], target = 6', '[0, 1]');

-- Reset sequence
SELECT setval('questions_id_seq', (SELECT MAX(id) FROM questions));
