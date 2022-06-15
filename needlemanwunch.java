import java.util.Arrays;
public class needlemanwunch{
class dna{
    int w; //gap penalty
    protected int[][] dp; //2d array to be filled with scores
    protected byte[][] trace; //2d byte array to keep track of the direction we wen tin the algorithm
    protected String a; // starter strings a and b. Strings we are comparing
    protected String b;
    protected String mid;
    protected String a2; // end strings. What happens after we build them.
    protected String b2;
    protected int optimal_alignment_score;

    public dna(String A, String B){
        a="."+A; b="."+B;                           // dots concatenated at the front of eachs tring serve to align the strings on the matrix
        dp = new int[a.length()][b.length()];
        trace = new byte[a.length()][b.length()];
    } // end constructor

    public int optimal(int[][] dp){                         // returns optimal alignment score
        return dp[a.length()-1][b.length()-1];
    }

    protected void fill(){              // fills our 2d matrix with the alignment scores
    w = 0;
    for (int i = 0; i<a.length();i++){
        for (int k = 0; k<b.length();k++){
            int a= 0x80000000;
            int b = 0x80000000;
            int c = 0x80000000;
            int def =  0x80000000;
            if (i==0 && k==0) {def = 0;}                        // default value for position (0,0) is zero
            if (i>0 && k>0) { a = dp[i-1][k-1]+score(i, k);} 
            if (i>0){b = dp[i-1][k]+w;}
            if (k>0) {c = dp[i][k-1]+w;}
            int max = Math.max(a,Math.max(b,c));
            max = Math.max(def,max);
            dp[i][k] = max;
            if (max==a){trace[i][k] = 1;}
            else if (max==b){ trace[i][k] = 2;}
            else if (max==c) { trace[i][k] = 3;}
            }
        }
            
    } //end fill

    public int score(int i, int k){
        char a = this.a.charAt(i);
        char b = this.b.charAt(k);
        if (a==b) {return 1;}
        else {return 0;}
    } // end score

    public void build(){
        int i = a.length()-1;
        int k = b.length()-1;
        a2 = "";
        mid = "";   // empty strings to rebuild with matches
        b2 = "";
        while (i!=0 && k!=0){
            int point = trace[i][k];
            if (point==1){
                a2 = a.charAt(i)+a2;
                b2 = b.charAt(k)+b2;
                mid = "|"+mid;
                i--;
                k--;
            }
             if (point==2){
                a2 = a.charAt(i)+a2;
                b2 = "-"+b2;
                mid = " "+mid;
                i--;
            }
             if (point==3){
                b2 = b.charAt(k)+b2;
                k--;
                a2 = "-"+a2;
                mid = " "+mid;
            } 
        }
        while (i>0){
            a2 = a.charAt(i)+a2;
            b2 = "-"+b2;
            mid = " "+mid;
            i--;
        }
        while (k>0){
            a2 = "-"+a2;
            b2 = b.charAt(k)+b2;
            mid = " "+mid;
            k--;
        }
        System.out.println(a2);
        System.out.println(mid);
        System.out.println(b2);
    }
} // end Dna

} //end needlmanwunch