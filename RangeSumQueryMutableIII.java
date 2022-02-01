import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;


/**
 * LeetCode 307. Range Sum Query - Mutable
 * https://leetcode.com/problems/range-sum-query-mutable/
 */
public class RangeSumQueryMutableIII {


    /**
     * Runtime: 98 ms, faster than 52.21% of Java online submissions.
     * Memory Usage: 68.4 MB, less than 98.99% of Java online submissions.
     * 
     * 15 / 15 test cases passed.
     * Status: Accepted
     * Runtime: 98 ms
     * Memory Usage: 68.4 MB
     */
    static class NumArray {


        /**
         * 
         */
        static class TreeNode {

            /**
             * Class members.
             */
            public int      sum;        // sum in this range
            public int      start;      // start index
            public int      end;        // end index
            public TreeNode left;
            public TreeNode right;

            /**
             * Constructor.
             */
            public TreeNode(int s, int e) {
                this.start  = s;
                this.end    = e;
            }

            /**
             * String representation.
             */
            @Override
            public String toString() {
                return String.format("(sum: %2d [%d : %d])", sum, start, end);
            }
        }


        /**
         * Class members.
         */
        TreeNode root = null;


        /**
         * Build the tree.
         * Recursive call.
         */
        private TreeNode buildTree(int[] nums, int start, int end) {

            // **** base case ****
            if (start > end) return null;

            // **** initialization ****
            TreeNode res = new TreeNode(start, end);

            // **** leaf node ****
            if (start == end) {
                res.sum = nums[start];
            } 
            
            // **** not a leaf node ****
            else {

                // **** compute mid point in range ****
                var mid = start + (end - start) / 2;

                // **** generate left child subtree ****
                res.left = buildTree(nums, start, mid);

                // **** generate right child subtree ****
                res.right = buildTree(nums, mid + 1, end);

                // **** update sum ****
                res.sum = res.left.sum + res.right.sum; 
            }

            // **** ****
            return res;
        }


        /**
         * Initializes the object with the integer array nums.
         */
        public NumArray(int[] nums) {
            root = buildTree(nums, 0, nums.length - 1);
        }
        

        /**
         * Recursive call.
         */
        private void update(TreeNode root, int i, int val) {

            // **** leaf node ****
            if (root.start == root.end) {
                root.sum = val;
            } else {

                // **** ****
                var mid = root.start + (root.end - root.start) / 2;

                // **** go left ****
                if (i <= mid) {
                    update(root.left, i, val);
                } 
                
                // **** go right ****
                else {
                    update(root.right, i, val);
                }

                // **** update sum ****
                root.sum = root.left.sum + root.right.sum;
            }
        }


        /**
         * Updates the value of nums[index] to be val.
         * Recursion entry point.
         */
        public void update(int i, int val) {
            update(root, i, val);
        }
        

        /**
         * 
         */
        private int query(TreeNode root, int i, int j) {

            // **** ****
            if (root.start == i && root.end == j) {
                return root.sum;
            } 
            
            // **** ****
            else {

                // **** compute mid value ****
                var mid = root.start + (root.end - root.start) / 2;

                // **** ****
                if (j <= mid) {
                    return query(root.left, i, j);
                }

                // **** ****
                else if (i >= mid + 1) {
                    return query(root.right, i , j);
                }

                // **** ****
                else {
                    return query(root.left, i, mid) + query(root.right, mid + 1, j);
                }
            }
        }


        /**
         * Returns the sum of the elements of nums 
         * between indices left and right inclusive 
         * (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
         */
        public int sumRange(int i, int j) {
            return query(root, i, j);
        }


        /**
         * In-order traversal.
         * Entry function.
         * 
         * !!! NOT PART OF THE SOLUTION !!!
         */
        public String inOrder(TreeNode root) {

            // **** initialization ****
            StringBuilder sb = new StringBuilder();

            // **** populate string builder ****
            inOrder(root, sb);

            // **** return string ****
            return sb.toString();
        }


        /**
         * In-order traversal.
         * Helper function.
         * 
         * !!! NOT PART OF THE SOLUTION !!!
         */
        private void inOrder(TreeNode root, StringBuilder sb) {

            // **** base case ****
            if (root == null) return;

            // **** traverse left ****
            inOrder(root.left, sb);

            // **** add node to string builder ****
            sb.append(root.toString() + "\n");

            // **** traverse right ****
            inOrder(root.right, sb);
        }


        /**
         * Post-order traversal.
         * Entry function.
         * 
         * !!! NOT PART OF THE SOLUTION !!!
         */
        public String postOrder(TreeNode root) {

            // **** initialization ****
            StringBuilder sb = new StringBuilder();

            // **** populate string builder ****
            postOrder(root, sb);

            // **** return string ****
            return sb.toString();
        }


        /**
         * Post-order traversal.
         * Helper function.
         * 
         * !!! NOT PART OF THE SOLUTION !!!
         */
        private void postOrder(TreeNode root, StringBuilder sb) {

            // **** base case ****
            if (root == null) return;

            // **** traverse left ****
            postOrder(root.left, sb);

            // **** traverse right ****
            postOrder(root.right, sb);

            // **** add node to string builder ****
            sb.append(root.toString() + "\n");
        }


        /**
         * Pre-order traversal.
         * Entry function.
         * 
         * !!! NOT PART OF THE SOLUTION !!!
         */
        public String preOrder(TreeNode root) {

            // **** initialization ****
            StringBuilder sb = new StringBuilder();

            // **** populate string builder ****
            preOrder(root, sb);

            // **** return string ****
            return sb.toString();
        }


        /**
         * Pre-order traversal.
         * Helper function.
         * 
         * !!! NOT PART OF THE SOLUTION !!!
         */
        private void preOrder(TreeNode root, StringBuilder sb) {

            // **** base case ****
            if (root == null) return;

            // **** add node to string builder ****
            sb.append(root.toString() + "\n");

            // **** traverse left ****
            preOrder(root.left, sb);

            // **** traverse right ****
            preOrder(root.right, sb);
        }


        /**
         * Level order tree traversal.
         * Returns string with tree representation.
         * 
         * !!! NOT PART OF THE SOLUTION !!!
         */
        public String levelOrder(TreeNode root) {

            // **** initialization ****
            StringBuilder sb                = new StringBuilder();
            LinkedList<TreeNode> primaryQ   = new LinkedList<>();
            LinkedList<TreeNode> secondaryQ = new LinkedList<>();

            // **** prime the primary queue ****
            primaryQ.offer(root);

            // **** loop while the primary queue is not empty O(n) ****
            while (!primaryQ.isEmpty()) {

                // **** remove head node ****
                TreeNode node = primaryQ.remove();

                // **** append node to string ****
                sb.append(node.toString() + " ");

                // **** offer left child ****
                if (node.left != null) secondaryQ.offer(node.left);

                // **** offer right child ****
                if (node.right != null) secondaryQ.offer(node.right);

                // **** swap queues if needed ****
                if (primaryQ.isEmpty() && !secondaryQ.isEmpty()) {

                    // **** primary points to secondary queue ****
                    primaryQ = secondaryQ;

                    // **** create new secondary queue ****
                    secondaryQ = new LinkedList<>();

                    // **** update string builder ****
                    sb.append("\n");
                }
            }

            // **** return string representation ****
            return sb.toString();
        }
    }


    /**
     * Test scaffold
     * @throws IOException
     * 
     * !!! NOT PART OF THE SOLUTION !!!
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read cmdArr ****
        String[] cmdArr = Arrays.stream(br.readLine().trim().split(","))
                                .map(cmd -> cmd.trim())
                                .toArray(size -> new String[size]);

        // **** for ease of use ****
        int m = cmdArr.length;

        // **** declare argArr ****
        int[][] argArr = new int[m][];

        // **** populate argArr ****
        for (int i = 0; i < m; i++)
            argArr[i] = Arrays.stream(br.readLine().trim().split(","))
                                .map(cmd -> cmd.trim())
                                .mapToInt(Integer::parseInt)
                                .toArray();
    
        // **** close buffered reader ****
        br.close();

        // **** compute n ****
        int n = argArr[0].length;
        
        // ???? ????
        System.out.println("main <<<      n: " + n);
        System.out.println("main <<<      m: " + m);
        System.out.println("main <<< cmdArr: " + Arrays.toString(cmdArr));
        System.out.println("main <<< argArr: ");
        for (int i = 0; i < argArr.length; i++)
            System.out.println(Arrays.toString(argArr[i]));

        // **** holds output ****
        Integer[] output = new Integer[m];

        // **** for starters ****
        NumArray numArray = null;

        // **** loop calling class methods ****
        for (int i = 0; i < m; i++) {
            switch (cmdArr[i]) {
                case "NumArray":
                    numArray = new NumArray(argArr[i]);

                    // ???? ????
                    System.out.println("main <<< root: " + numArray.root.toString());
                    System.out.println("main <<< preOrder: ");
                    System.out.print(numArray.preOrder(numArray.root));
                    System.out.println("main <<< levelOrder:");
                    System.out.println(numArray.levelOrder(numArray.root));
                break;

                case "sumRange":
                    output[i] = numArray.sumRange(argArr[i][0], argArr[i][1]);
                break;

                case "update":
                    numArray.update(argArr[i][0], argArr[i][1]);
                break;

                default:
                    System.out.println("main <<< UNEXPECTED cmdArr[" + i + "] ==>" + cmdArr[i] + "<==");
                break;
            }
        } 

        // **** display output ****
        System.out.println("main <<< output: " + Arrays.toString(output));
    }
    
}
