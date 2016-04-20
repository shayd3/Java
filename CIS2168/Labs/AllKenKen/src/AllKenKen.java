import java.util.*;

public class AllKenKen
{
    private ListItem L, tail; private boolean[][] Row, Col;
    private int[][] Board; private String[][] Problem; 
    private int n; private int ctr;

    public static void main(String[] args)
    {
       AllKenKen K=new AllKenKen(); 
       K.solve(K.L); 
       System.out.println("# of solutions is "+K.ctr);       
    }
   
    private AllKenKen()
    {
       Scanner in=new Scanner(System.in);
       System.out.println("Enter n");
       n=in.nextInt();System.out.println("n is "+n); ctr=0;  
       Row=new boolean[n+1][n+1]; Col=new boolean[n+1][n+1];
       Board=new int[n+1][n+1]; Problem=new String[n+1][n+1];
       for (int i=1; i<n+1; i++)
          for (int j=1; j<n+1; j++)
             Row[i][j]=Col[i][j]=true; 
       int k=n*n; int op, value, i, j, size, s; L=null;
       ListItem p, q, prev; String entry;
       System.out.println("Enter op,value,size, and pairs");
       while (k>0)
       {
          op=in.nextInt(); value=in.nextInt(); s=0; size=in.nextInt();
          if (op==0) entry=" "+value;
          else if (op==1) entry="/"+value;
          else if (op==2) entry="-"+value;
          else if (op==3) entry="+"+value;
          else entry="*"+value;
          if (size>1)
          {
              pair[] slots=new pair[size]; 
              while (s<size)
              {
                 i=in.nextInt(); j=in.nextInt();
                 Problem[i][j]=entry; 
                 slots[s]=new pair(i, j); s++;
              }
              p=new ListItem(op, value, size, slots); prev=null; q=L;
              while ((q!=null) && (size>=q.size)) {prev=q; q=q.link;}
              if (prev!=null) 
              {
                 p.link=q; prev.link=p; p.back=prev; 
                 if (q==null) tail=p;
              }
              else {p.link=L; L=p; p.back=null;}
          }
          else
          { 
               i=in.nextInt(); j=in.nextInt(); 
               Problem[i][j]=entry; Board[i][j]=value;
               Row[i][value]=Col[j][value]=false; 
          }
          k=k-size;
       }
       for (int r=1; r<=n; r++)
       {
          for (int c=1; c<=n; c++) 
          {
             while(Problem[r][c].length()!=4) Problem[r][c]=Problem[r][c]+" ";
             System.out.print(Problem[r][c]);
          }
          System.out.println();
       }
       System.out.println();
       p=L; int f=0; prev=null;
       while (p!=null) {f++; p.c=f; p.back=prev; prev=p; p=p.link;}
    }

    public void solve(ListItem p) 
    {
       while (p!=null) 
       { 
           boolean result=nextnum(p);
           if (result) {if (p.link==null) {print();} else p=p.link;} else p=p.back;
       }
    }

    public void print()
    {
       ListItem q, p=L; ctr++;
       while (p!=null)
       {
          for (int k=0; k<p.size; k++)
          {
             int i=p.slots[k].getrow(); int j=p.slots[k].getcol();
             Board[i][j]=p.slots[k].getnum();
          }
          p=p.link;
       }
       System.out.println("Solution "+ctr+":");
       for (int r=1; r<=n; r++)
       {
          for (int c=1; c<=n; c++)
            System.out.print(" "+Board[r][c]+" ");
          System.out.println();
       }
    }

    public boolean avail(int g, int i, int j)
    {return ((g>=1)&&(g<=n)&&(Row[i][g])&&(Col[j][g]));}

    public boolean nextnum(ListItem p)
    {
        boolean result=false; int i, j, I, J, last=p.last;
        int v=p.value; int k=p.slots[last].num; int K=p.slots[last+1].num;  
        if (k!=0)
        {
           for (int d=last; d<p.size; d++)
           {
               int g=p.slots[d].num;
               i=p.slots[d].getrow(); j=p.slots[d].getcol();
               Row[i][g]=Col[j][g]=true; 
           }
        }
        i=p.slots[last].getrow(); j=p.slots[last].getcol();
        I=p.slots[last+1].getrow(); J=p.slots[last+1].getcol();
        if (p.op==1) // /
        {
           if (k==0) {k=1; K=0;} boolean done=false;
           while ((k<=n)&&(K<=n)&&!done)
           {   
              if (k>K) {k=K+1; K=v*k;} else {K=k; k=v*K;}
              result=((k<=n)&&(K<=n)&&avail(k, i, j)&&avail(K, I, J));
              if (result)
              { 
                 p.slots[0].num=k; p.slots[1].num=K;
                 Row[i][k]=Col[j][k]=Row[I][K]=Col[J][K]=false; done=true;
              }
           }
           result=((k<=n)&&(K<=n)); if (!result) p.slots[last].num=0; 
        }
        else if (p.op == 2)
        {
           if (k==0) {k=1; K=0;} boolean done=false; 
           while ((k<=n)&&(K<=n)&&!done)
           {
              if (k>K) {k=K+1; K=v+k;} else {K=k; k=v+K;}
              result=((k<=n)&&(K<=n)&&avail(k, i, j)&&avail(K, I, J));
              if (result)
              {
                  p.slots[0].num=k; p.slots[1].num=K;
                  Row[i][k]=Col[j][k]=Row[I][K]=Col[J][K]=false; done=true;
              }
           }
           result=((k<=n)&&(K<=n)); if (!result) p.slots[last].num=0;                 
        }
        else if (p.op == 3) // +
        {
           int sum=0; int cell=last;
           for (int h=0; h<last; h++) sum=sum+p.slots[h].num; 
           k=p.slots[cell].num+1; int end=p.size-1; boolean done=false;
           while ((cell>=0)&&(cell<=end)&&!done)
           {
              i=p.slots[cell].getrow(); j=p.slots[cell].getcol();
              if (cell<end)
              {
                 if (sum+k<v)
                 {
                    if (avail(k,i,j)) 
                    {
                       int b=p.slots[cell].num; Row[i][b]=Col[j][b]=true; 
                       p.slots[cell].num=k; Row[i][k]=Col[j][k]=false;
                       sum=sum+k; cell++; k=1;  
                    }
                    else 
                    {
                       if (k<n) k++; 
                       else 
                       {
                          int b=p.slots[cell].num; Row[i][b]=Col[j][b]=true; 
                          p.slots[cell].num=0; 
                          cell--; 
                          if (cell>=0) 
                          {
                             k=p.slots[cell].num+1; sum=sum-(k-1);
                          }
                       }
                    }
                 }
                 else
                 {
                    int m=p.slots[cell].num; Row[i][m]=Col[j][m]=true;
                    p.slots[cell].num=0; 
                    cell--; 
                    if (cell>=0) 
                    {
                       k=p.slots[cell].num+1; sum=sum-(k-1);
                    } 
                 }
              }
              else
              {
                 k=v-sum;
                 if (avail(k,i,j))                 
                 {
                    int b=p.slots[cell].num; Row[i][b]=Col[j][b]=true; 
                    p.slots[cell].num=k; Row[i][k]=Col[j][k]=false;
                    p.last=end-1; done=true;
                 }
                 else 
                 {
                    int m=p.slots[cell].num; Row[i][m]=Col[j][m]=true;
                    p.slots[cell].num=0; 
                    cell--; 
                    if (cell>=0) 
                    {
                       i=p.slots[cell].getrow(); j=p.slots[cell].getcol();
                       m=p.slots[cell].num; Row[i][m]=Col[j][m]=true;
                       k=p.slots[cell].num+1; sum=sum-(k-1);
                    }
                 }
              }
           }
           result=done; if (!result) {p.slots[0].num=0; p.last=0;}
    }           
    else // *
    {
       int prod=1; int cell=last;
       for (int h=0; h<last; h++) prod=prod*p.slots[h].num; 
       k=p.slots[cell].num+1; int end=p.size-1; boolean done=false;
       while ((cell>=0)&&(cell<=end)&&!done)
       {
          i=p.slots[cell].getrow(); j=p.slots[cell].getcol();
          if (cell<end)
          { 
             if (prod*k<=v) 
             {
                 if (avail(k,i,j))
                 {
                    int b=p.slots[cell].num; Row[i][b]=Col[j][b]=true; 
                    p.slots[cell].num=k; Row[i][k]=Col[j][k]=false;
                    prod=prod*k; cell++; k=1;  
                 }
                 else 
                 {  
                    if (k<n) k++; 
                    else 
                    {
                       int b=p.slots[cell].num; Row[i][b]=Col[j][b]=true;  
                       cell--; 
                       if (cell>=0) 
                       {k=p.slots[cell].num+1; if (k>1) prod=prod/(k-1);}
                    }
                 }
             }
             else
             {
                 int m=p.slots[cell].num; Row[i][m]=Col[j][m]=true;
                 cell--; 
                 if (cell>=0) 
                 {
                     k=p.slots[cell].num+1; if (k>1) prod=prod/(k-1);
                 }        
             }
           }   
           else
           { 
                  k=v/prod;
                  if ((k*prod==v)&&avail(k, i, j))
                  {
                     int b=p.slots[cell].num; Row[i][b]=Col[j][b]=true; //????
                     p.slots[cell].num=k; Row[i][k]=Col[j][k]=false;
                     p.last=end-1; done=true;
                  }           
                  else 
                  {              
                     int m=p.slots[cell].num; Row[i][m]=Col[j][m]=true;
                      cell--; 
                      if (cell>=0) 
                      {
                         i=p.slots[cell].getrow(); j=p.slots[cell].getcol(); 
                         m=p.slots[cell].num; Row[i][m]=Col[j][m]=true;
                         k=p.slots[cell].num+1; prod=prod/(k-1);
                      }
                  }
              // }
               }//end of cell<end
            }//end while
            result=done;  if (!result) {p.slots[0].num=0; p.last=0;}
        } //4-2   
        if (!result)
           for (int d=0; d<p.size; d++)   
           {
              int g=p.slots[d].num;
              i=p.slots[d].getrow(); j=p.slots[d].getcol();
              Row[i][g]=Col[j][g]=true;  
           }
        return result;
    }//end nextnum      
 
    private class ListItem
    {
        private int op; private int value;
        private int size; private int last;
        private pair[] slots; private ListItem link;
        private ListItem back; private int c;

        private ListItem(int Op, int v, int s, pair[] Slots)
        {
           op=Op; value=v; size=s; last=0; slots=Slots;
        } 
    }//end ListItem  
     
    private class pair
    {
       private int row; private int col; private int num;

       private pair(int i, int j) {row=i; col=j; num=0;}
       private int getrow() {return row;}
       private int getcol() {return col;} 
       private int getnum() {return num;}
    }//end pair
}//end KenKen