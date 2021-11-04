import java.util.*;
import java.lang.*;
class Main {
  public static void main(String[] args) {
    PriorityQueue<Student> eg = new PriorityQueue<>();
    for(int i = 0; i < 10; i++){
      Student x = new Student("Student" +i, (int)(Math.random()*100 +1));
      eg.add(x);
    }
    Student s1 = new Student("Ajay", 100);
    eg.add(s1);
    System.out.println(eg);
    eg.poll();
    System.out.println(eg);
    eg.poll();
    System.out.println(eg);
    eg.remove(s1);
    System.out.print(eg);
  }
}
class PriorityQueue<T extends Comparable<T>>{
  private ArrayList<T> x;
  public PriorityQueue(){
    x = new ArrayList<T>();
  }
  public void add(T val){
    x.add(val);
    int ind = x.size()-1;
    if(ParentInd(ind) < 0){
      return;
    }
    while(val.compareTo(x.get(ParentInd(ind))) > 0){
      swap(ind,ParentInd(ind));
      ind = ParentInd(ind);
      if(ParentInd(ind) < 0){
        return;
      }
    }
  }
  public int remove(T val){
    int ind = x.indexOf(val);
    if (ind == -1)
       return -1;
    swap(ind,x.size()-1);
    x.remove(x.size()-1);
    int swapInd = ind;
    int varLChildInd = LChildInd(swapInd);
    int varRChildInd = RChildInd(swapInd);
    if(varLChildInd == -1 && varRChildInd == -1){
      return ind;
    }
    while(x.get(swapInd).compareTo(x.get(varLChildInd)) < 0 || x.get(swapInd).compareTo(x.get(varRChildInd)) < 0){
      T RChild = x.get(varRChildInd);
      T LChild = x.get(varLChildInd);
      if(RChild.compareTo(LChild)<0){
        swap(swapInd,varLChildInd);
        swapInd = varLChildInd;
      }
      else{
        swap(swapInd,varRChildInd);
        swapInd = varRChildInd;
      }
      varLChildInd = LChildInd(swapInd);
      varRChildInd = RChildInd(swapInd);
      if(varLChildInd != -1 && varRChildInd != -1){
        continue;
      }
      else {
        return ind;
      }
    }
    return ind;
  }
  public void poll(){
    remove(x.get(0));
  } 
  private int ParentInd(int ind){
    double i = (ind-1)/2.0;
    if(i<0){
      return -1;
    }
    return (int) i;
  }
  private int LChildInd(int ind){
    ind = 2*ind+1;
    if(ind >= x.size()){
      return -1;
    }
    return ind;
  }
  private int RChildInd(int ind){
    ind = 2*ind+2;
    if(ind >= x.size()){
      return -1;
    }
    return ind;
  }
  private void swap(int indA, int indB){
    if(indA == -1 || indB == -1){
      return;
    }
    T temp = x.get(indA);
    x.set(indA,x.get(indB));
    x.set(indB,temp);
  }
  public String toString(){
    return x.toString();
  }
}
class Student implements Comparable<Student>{
  private String name;
  public double grade;
  Student(String nm, double gr) {
    name = nm;
    grade = gr;
  }
  public int compareTo(Student o) {
    return (int)(this.grade-o.grade);
  }

  public boolean equals(Student o) {
     return this.name.equals(o.name);
  }

  public String toString() {
    return this.name + " " + this.grade;
  }

}