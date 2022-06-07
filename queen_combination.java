import java.util.List;

class queen_combination{
    //tnq=total number of queens
    //tnb=total number of boxes
    public static int queen_combination1D(int tnq, int tnb, int qno, int bno, String ans){
        if(qno==tnq){
            System.out.println(ans);
            return 1;
        }
    int count=0;
     for(int i=bno; i<tnb; i++){
         ans=ans+'q'+qno+'b'+bno;
        count+= queen_combination1D(tnq, tnb, qno+1, bno+1, ans);
     }
     return count;
    }
    // Maintaining a visited array to keep an account for all the boxes that have been used to place the queen. 
    public static int queen_permutation1D(int tnq, int tnb, int qno, int bno, String ans, int[]vis){
        if(qno==tnq){
            System.out.println(ans);
            return 1;
        }
        int count=0;
        for(int i=bno; i<tnb;i++){
            if(vis[i]!=1){
                //Marking the box before recursive call
                vis[i]=1;
                
               count+= queen_permutation1D(tnq, tnb, qno+1, 0, ans+'q'+qno+'b'+i, vis);
               //Unmarking the box after recursive call so different order of queen placement can be explored.
                vis[i]=0;
            }
        }
        return count;
    }
    public static int queen_combination2D(int tnq, int[][]boxes, int qno, int bno, String ans){
        if(qno==tnq){
            System.out.println(ans);
            return 1;
        }
        int n=boxes.length; //no of rows
        int m=boxes[0].length; //no of columns
        int count=0;
        for(int idx=bno; idx<n*m; idx++){
            int r=idx/m;
            int c=idx%m;
           count+= queen_combination2D(tnq, boxes, qno+1, idx+1, ans + '('+ r + ',' + c + ')');
        }
        return count;
    }
    public static int queen_permutation2D(int tnq, int[][] boxes, int qno, int bno, String ans){
        if(qno==tnq){
            System.out.println(ans);
            return 1;
        }
    
        int n=boxes.length; //no of rows
        int m=boxes[0].length; //no of columns
        int count=0;
        for(int idx=bno; idx<n*m;idx++){
            int r=idx/m;
            int c=idx%m;
            if(boxes[r][c]!=1){
                //Marking the box before recursive call
                boxes[r][c]=1;
                
               count+= queen_permutation2D(tnq, boxes, qno+1, 0, ans + '('+ r + ',' + c + ')');
               //Unmarking the box after recursive call so different order of queen placement can be explored.
                boxes[r][c]=0;
            }
        }
        return count;
    }


    //Queen conmbination 2D with subsequence method-option to come or not
    public static int queen_sub_combi2D(int tnq, int[][]boxes, int qno, int bno, String ans){
        int n=boxes.length;
        int m=boxes[0].length;
        if(qno==tnq || bno==m*n){
            if(qno==tnq){
            System.out.println(ans);
            return 1;
        }
        return 0;
        }
        int count=0;
        int r=bno/m;
        int c=bno%m;
        count+=queen_sub_combi2D(tnq, boxes, qno+1, bno+1, ans + '('+ r + ',' + c + ')');
        count+=queen_sub_combi2D(tnq, boxes, qno, bno+1, ans);
        return count;
    }
    //*********************************************************************************************************************************** */
   
   //n-queen-combination
   public static boolean isSafetoPlaceQueen(int[][]board, int r, int c){
       int[][]dir={{0,-1},{-1,-1},{-1,0},{-1,1}};
       int m=board.length;
       

       for(int i=0;i<dir.length;i++){
           for(int rad=1;rad<m;rad++){
               int x=r+rad*dir[i][0];
               int y=c+rad*dir[i][1];
               if(x>=0 && x<m && y>=0 && y<m){
                   if(board[x][y]==1){
                       return false;
                   }
               }
               else{
                   break;
               }
           }
       }
       return true;
   }
   
   
   public static int n_queen(int tnq, int[][]board,  int bno, String ans){
    if(tnq==0){
        System.out.println(ans);
        return 1;
    }
    int n=board.length; //no of rows
    int count=0;
    for(int idx=bno; idx<n*n; idx++){
        int r=idx/n;
        int c=idx%n;
        if(isSafetoPlaceQueen(board, r, c)){
            board[r][c]=1;
            count+= n_queen(tnq-1, board, idx+1, ans + '('+ r + ',' + c + ')');
            board[r][c]=0;
        }
       
    }
    return count;
   
}
   
 //n-queen permutation
 public static boolean isSafetoPlaceQueen_permu(int[][]board, int r, int c){
    //8 directions will be checked since the queen can be arranged in any order now
    int[][]dir={{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
    int m=board.length;
    

    for(int i=0;i<dir.length;i++){
        for(int rad=1;rad<m;rad++){
            int x=r+rad*dir[i][0];
            int y=c+rad*dir[i][1];
            if(x>=0 && x<m && y>=0 && y<m){
                if(board[x][y]==1){
                    return false;
                }
            }
            else{
                break;
            }
        }
    }
    return true;
}
public static int n_queen_permu(int tnq, int[][]board,  int bno, String ans){
    if(tnq==0){
        System.out.println(ans);
        return 1;
    }
    int n=board.length; //no of rows
    int count=0;
    for(int idx=bno; idx<n*n; idx++){
        int r=idx/n;
        int c=idx%n;
        //checking if it is safe to place the queen on a box as well as that the box has not been occupied already
        //1 if the box has been occupied
        //0 if the box hasn't been occupied
        if(isSafetoPlaceQueen_permu(board, r, c) && board[r][c]!=1){
            board[r][c]=1;
            count+= n_queen_permu(tnq-1, board, 0, ans + '('+ r + ',' + c + ')');
            board[r][c]=0;
        }
       
    }
    return count;
   
}


//combination on the basis of room
//n_queen shadow optimisation-instead of checking for each direction using direction matric we will use vector matrices for row, column, diagonal, antidiagonal
static boolean[]row;
static boolean[]column;
static boolean[]diag;
static boolean[]antiDiag;



public static int n_queen_combi2(int tnq, int[][]board, int qno, int idx, String ans){
    if(qno==tnq){
        System.out.println(ans);
        return 1;
    }
    int count=0;
    int n=board.length;
    int m=board[0].length;
    for(int i=idx;i<n*n;i++){
        int r=i/n;
        int c=i%n;
        if(!row[r] && !column[c] && !diag[r-c+m-1] && !antiDiag[r+c]){
            row[r]=column[c]=diag[r-c+m-1]=antiDiag[r+c]=true;
            count+=n_queen_combi2(tnq, board, qno+1, i+1, ans+'('+ r + ',' + c + ')');
            row[r]=column[c]=diag[r-c+m-1]=antiDiag[r+c]=false;
        }
    }
    return count;
}




//combination on the basis of floor
//n-queen more optimised: here a queen is given an option to choose only the cells available on a particular floor hence instead of checking for cells for the whole board we only check the cells for a particular floor
public static int n_queen_combi3(int tnq, int[][]board, int qno, int rno, String ans){
    if(qno==tnq){
        System.out.println(ans);
        return 1;
    }
    int count=0;
    int n=board.length;
    int m=board[0].length;
    for(int col=0;col<m;col++){
        int r=rno;
        int c=col;
        //although we don't need row's check since floors will be assigned in order due to combination solution
        if(!row[r] && !column[c] && !diag[r-c+m-1] && !antiDiag[r+c]){
            row[r]=column[c]=diag[r-c+m-1]=antiDiag[r+c]=true;
            count+=n_queen_combi3(tnq, board, qno+1, rno+1, ans+'('+ r + ',' + c + ')');
            row[r]=column[c]=diag[r-c+m-1]=antiDiag[r+c]=false;
        }
    }
    return count;

}



//permutation on the basis of floor
//here the queen has the option o choose between floors as well
//n-queen more optimised: here a queen is given an option to choose only the cells available on a particular floor hence instead of checking for cells for the whole board we only check the cells for a particular floor
//two calls are made: if the queen decides to come
//if the queen doesn't decide to come

public static int n_queen_permu3(int tnq, int[][]board, int qno, int rno, String ans){
    //rno==0: incase the queen decies to skip all the floors
    if(qno==tnq || rno==board.length){
        if(qno==tnq){
        System.out.println(ans);
        return 1;
    }
    return 0;
    }
    int count=0;
    int n=board.length;
    int m=board[0].length;
    for(int col=0;col<m;col++){
        int r=rno;
        int c=col;
        
        if(!row[r] && !column[c] && !diag[r-c+m-1] && !antiDiag[r+c]){
            row[r]=column[c]=diag[r-c+m-1]=antiDiag[r+c]=true;
            count+=n_queen_permu3(tnq, board, qno+1, 0, ans+'('+ r + ',' + c + ')');
            row[r]=column[c]=diag[r-c+m-1]=antiDiag[r+c]=false;
        }
    }
    count+=n_queen_permu3(tnq, board, qno, rno+1, ans);
    return count;

}



//Leetcode-39
public void Combinations(int []arr, int idx, int target,List<List<Integer>>ans, List<Integer>smallans){
    if(target==0){
        //new List created because the value of smallans keeps getting updated throughout recursion and ans would contain same combinations if we add smallans directly
        List<Integer>base=new Arraylist<>(smallans);
        ans.add(base);
        return;

    }
    for(int i=idx; i<arr.length;i++){
        if(target-arr[i]>=0){
            smallans.add(arr[i]);
            Combinations(arr, i, target-arr[i], ans, smallans);
            //removing elements so that new combinations can be explored
            smallans.remove(arr[i]);
        }

    }
}
public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>>ans=new ArrayList<>();
    List<Integer>smallans=new ArrayList<>();
    Combinations(candidates, 0, target, ans, smallans);
    return ans;

        
}


//Leetcode-40
public void combinationDuplicates(int[]arr,int idx, int target, List<Integer>smallans, List<List<Integer>>ans){
    if(target==0){
        List<Integer>base=new ArrayList<>(smallans);
        ans.add(base);
        return;
    }
    int prev=-1;
    
    for(int i=idx;i<arr.length;i++){
        if(prev!=arr[i] && target-arr[i]>=0){
            smallans.add(arr[i]);
            combinationDuplicates(arr, i+1, target-arr[i],smallans,ans);
            smallans.remove(smallans.size()-1);
        }
        prev=arr[i];
        
    }
}
public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    List<List<Integer>>ans=new ArrayList<>();
    List<Integer>smallans=new ArrayList<>();
    combinationDuplicates(candidates, 0,target, smallans, ans);
    return ans;
}
//Leetcode-322
public int coinNumbers(int[] coins, int idx, int amount) {
    if(amount==0){
        return 0;
    }
    int minCoin=(int)1e9;
    for(int i=idx; i<coins.length;i++){
        if(amount-coins[i]>=0){
        int smallans=coinNumbers(coins, i,amount-coins[i]);
            //To check if base case was hit otherwise value will be updated unnecessarily
        if(smallans!=(int)1e9 && smallans+1<minCoin){
            minCoin=smallans+1;
        }
        }
    }
    return minCoin;
    
}
public int coinChange(int[] coins, int amount){
    return coinNumbers(coins, 0,amount);
}
    public static void main(String[] args) {
        int tnq=4, tnb=5;
        //combination1d
        // int ans= queen_combination1D(tnq, tnb, 0, 0, "");
        // System.out.println(ans);



        //permutation1D
        // int[]vis=new int[tnb];
        // int ans=queen_permutation1D(tnq, tnb, 0, 0, "", vis);
        // System.out.println(ans);



        //combination2D
        // int[][]boxes=new int[4][4];
        // int ans=queen_combination2D(tnq, boxes, 0, 0, "");
        // System.out.println(ans);
        // int[][]boxes=new int[4][4];
        // int ans=queen_sub_combi2D(tnq, boxes, 0, 0, "");
        //  System.out.println(ans);
          



       //n-queen
    //    int[][]board=new int[4][4];

    //    int ans=n_queen_permu(tnq, board, 0, "");
    //    System.out.println(ans);




    //shadow
    int[][] board=new int[4][4];
    int n=board.length;
    int m=board[0].length;
    row=new boolean[n];
    column=new boolean[m];
    diag=new boolean[n+m-1];
    antiDiag=new boolean[n+m-1];
    int ans=n_queen_permu3(tnq, board, 0,0, "");
      System.out.println(ans);

    }
}