const TEMPLATES = [
  {
    name: 'int[] + int → int[]',
    example: 'Two Sum',
    methodSignature: `    public int[] solve(int[] nums, int target) {
        // your solution here
        return new int[]{};
    }`,
    driverCode: `    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        int target = sc.nextInt();
        int[] result = new Solution().solve(nums, target);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            if (i > 0) sb.append(" ");
            sb.append(result[i]);
        }
        System.out.println(sb.toString());
    }`,
    inputFormat: 'Line 1: n (array length)\nLine 2: n space-separated ints\nLine 3: target int',
  },
  {
    name: 'int[] → int',
    example: 'Max Subarray, Best Time to Buy/Sell',
    methodSignature: `    public int solve(int[] nums) {
        // your solution here
        return 0;
    }`,
    driverCode: `    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        System.out.println(new Solution().solve(nums));
    }`,
    inputFormat: 'Line 1: n (array length)\nLine 2: n space-separated ints',
  },
  {
    name: 'int[] → boolean',
    example: 'Contains Duplicate',
    methodSignature: `    public boolean solve(int[] nums) {
        // your solution here
        return false;
    }`,
    driverCode: `    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        System.out.println(new Solution().solve(nums));
    }`,
    inputFormat: 'Line 1: n (array length)\nLine 2: n space-separated ints',
  },
  {
    name: 'int[] → int[]',
    example: 'Product of Array Except Self',
    methodSignature: `    public int[] solve(int[] nums) {
        // your solution here
        return new int[]{};
    }`,
    driverCode: `    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        int[] result = new Solution().solve(nums);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            if (i > 0) sb.append(" ");
            sb.append(result[i]);
        }
        System.out.println(sb.toString());
    }`,
    inputFormat: 'Line 1: n (array length)\nLine 2: n space-separated ints',
  },
  {
    name: 'int[] + int → int',
    example: 'Binary Search, Subarray Sum Equals K',
    methodSignature: `    public int solve(int[] nums, int target) {
        // your solution here
        return 0;
    }`,
    driverCode: `    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        int target = sc.nextInt();
        System.out.println(new Solution().solve(nums, target));
    }`,
    inputFormat: 'Line 1: n (array length)\nLine 2: n space-separated ints\nLine 3: target int',
  },
  {
    name: 'int[] + int → boolean',
    example: 'Pair Sum, Target Sum',
    methodSignature: `    public boolean solve(int[] nums, int target) {
        // your solution here
        return false;
    }`,
    driverCode: `    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        int target = sc.nextInt();
        System.out.println(new Solution().solve(nums, target));
    }`,
    inputFormat: 'Line 1: n (array length)\nLine 2: n space-separated ints\nLine 3: target int',
  },
  {
    name: 'String → int',
    example: 'Longest Substring Without Repeating',
    methodSignature: `    public int solve(String s) {
        // your solution here
        return 0;
    }`,
    driverCode: `    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(new Solution().solve(s));
    }`,
    inputFormat: 'Line 1: string s',
  },
  {
    name: 'String → String',
    example: 'Reverse String, Valid Palindrome',
    methodSignature: `    public String solve(String s) {
        // your solution here
        return "";
    }`,
    driverCode: `    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(new Solution().solve(s));
    }`,
    inputFormat: 'Line 1: string s',
  },
  {
    name: 'String → boolean',
    example: 'Valid Parentheses, Valid Anagram',
    methodSignature: `    public boolean solve(String s) {
        // your solution here
        return false;
    }`,
    driverCode: `    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(new Solution().solve(s));
    }`,
    inputFormat: 'Line 1: string s',
  },
  {
    name: 'String + String → boolean',
    example: 'Valid Anagram, Isomorphic Strings',
    methodSignature: `    public boolean solve(String s, String t) {
        // your solution here
        return false;
    }`,
    driverCode: `    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String t = sc.nextLine();
        System.out.println(new Solution().solve(s, t));
    }`,
    inputFormat: 'Line 1: string s\nLine 2: string t',
  },
  {
    name: 'int[][] → int',
    example: 'Number of Islands, Unique Paths',
    methodSignature: `    public int solve(int[][] matrix) {
        // your solution here
        return 0;
    }`,
    driverCode: `    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                matrix[i][j] = sc.nextInt();
        System.out.println(new Solution().solve(matrix));
    }`,
    inputFormat: 'Line 1: rows cols\nFollowing lines: matrix values space-separated',
  },
  {
    name: 'int + int → int',
    example: 'Climbing Stairs, Fibonacci',
    methodSignature: `    public int solve(int n) {
        // your solution here
        return 0;
    }`,
    driverCode: `    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(new Solution().solve(n));
    }`,
    inputFormat: 'Line 1: int n',
  },
  {
    name: 'int[] + int[] → int[]',
    example: 'Merge Sorted Arrays',
    methodSignature: `    public int[] solve(int[] nums1, int[] nums2) {
        // your solution here
        return new int[]{};
    }`,
    driverCode: `    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int[] nums1 = new int[n1];
        for (int i = 0; i < n1; i++) nums1[i] = sc.nextInt();
        int n2 = sc.nextInt();
        int[] nums2 = new int[n2];
        for (int i = 0; i < n2; i++) nums2[i] = sc.nextInt();
        int[] result = new Solution().solve(nums1, nums2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            if (i > 0) sb.append(" ");
            sb.append(result[i]);
        }
        System.out.println(sb.toString());
    }`,
    inputFormat: 'Line 1: n1\nLine 2: n1 space-separated ints\nLine 3: n2\nLine 4: n2 space-separated ints',
  },
  {
    name: 'String[] → String[]',
    example: 'Group Anagrams',
    methodSignature: `    public List<String> solve(String[] strs) {
        // your solution here
        return new ArrayList<>();
    }`,
    driverCode: `    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) strs[i] = sc.nextLine();
        List<String> result = new Solution().solve(strs);
        for (String s : result) System.out.println(s);
    }`,
    inputFormat: 'Line 1: n (array length)\nFollowing n lines: one string each',
  },
  {
    name: 'Custom (write your own)',
    example: '',
    methodSignature: '',
    driverCode: '',
    inputFormat: '',
  },
];

export default TEMPLATES;
