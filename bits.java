import java.util.List;
public class bits {
    //Leetcode-191

        // you need to treat n as an unsigned value
        //will run 32 times
        //comlpexity-O(n)
        public int hammingWeight01(int n) {
            int i = 1;
            int count = 0;
            for (int j = 1; j <= 32; j++) {
                if ((i & n) != 0)
                    count++;
                i = (i << 1);

            }
            return count;
        }
        //will run until n becomes zero
        //O(log n)
        public int hammingWeight02(int n) {
            int count = 0;

            while (n != 0) {
                if ((n & 1) != 0)
                    count++;

                n = n >> 1;
            }
            return count;
        }
        //x&(x-1) technique
        //will only run as many times as there are zeroes in the number
        //log n= equal to the number of set bits
        public int hammingWeight(int n) {
            int count = 0;
            while (n != 0) {
                count++;
                n = n & (n - 1); //this operation will turn the last set bit 0
            }
            return count;
        }



        //Leetcode-338
        public int[] countBits(int n) {
            int[]ans=new int [n+1];
            for(int i=1;i<=n;i++){
                ans[i]=ans[(i&(i-1))]+1;
                //if i contains 1 bit
                //(i&(i-1)) contains p-1 bits
            }
            return ans;
        }


        //power of two
        //all number that are power of two have only 1 set bit
        //n&(n-1) sets the last set bit 0 so result of this for all power of 2 numbers will be 0
        public boolean isPowerOfTwo(int n) {
            if (n>0 && (n&(n-1))==0 ) return true;
            return false;
        }
        //power of four
        public boolean isPowerOfFour(int n) {
            // checking if n is zero or not a power of two
            if (n <= 0 || (n & (n - 1)) != 0)
                return false;
            int count = 0;
            int num = n;
            // counting the number of zeroes
            // in power of four-number of zeroes is always even
            while (num != 0) {
                if ((num & 1) == 0)
                    count++;
                num >>>= 1;
            }

            return count % 2 == 0;
        }
}
