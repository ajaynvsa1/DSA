package com.company;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String tbe = in.nextLine(); //toBeEncoded
        EnRes encoded = encode(tbe);
        String encodedString = encoded.encodedString;
        String hbd = decode(encoded); //hasBeenDecoded
        System.out.println("Encoded String: " + encodedString);
        System.out.println("Decoded String: " + hbd);
        if(hbd.equals(tbe)){
            System.out.print("Success");
        }
        else{
            System.out.print("Failure");
        }
    }

    /*returns EnRes (Encoded Result) given string
    to be encoded containing encoded string and table*/
    static EnRes encode(String tbe){
        StringBuilder result = new StringBuilder();
        Node root = getNewEncodeTree(tbe);
        HashMap<Character,String> cs = new HashMap<>();
        ArrayList<Node> q = new ArrayList<>();
        q.add(root);
        while(!q.isEmpty()){ //Treats tree as graph and does BFS
            Node curr = q.remove(0);
            Node toAddA = curr.left;
            Node toAddB = curr.right;
            if(toAddA != null){
                if(!toAddA.visited){
                    q.add(toAddA);
                    toAddA.visited = true;
                    toAddA.path = curr.path+"0"; //Add 0 to path if left
                }
            }
            if(toAddB != null){
                if(!toAddB.visited){
                    q.add(toAddB);
                    toAddB.visited = true;
                    toAddB.path = curr.path+"1"; //Add 1 to path if right
                }
            }
            if(toAddA == null && toAddB == null){
                cs.put(curr.c,curr.path); //If leaf put encoding of character (path) into map
            }
        }
        int len = tbe.length();

        //Builds encodedString one encoded character at a time
        for(int i = 0; i < len; i++){
           result.append(cs.get(tbe.charAt(i)));
        }
        EnRes resres = new EnRes();
        resres.encodedString = result.toString();
        HashMap<String,Character> inverseCS = new HashMap<>();

        //Debug and create inverse map for table
        for (Map.Entry<Character, String> entry : cs.entrySet()) {
            Character key = entry.getKey();
            String val = entry.getValue();
            System.out.println(key+" "+val);
            inverseCS.put(val, key);
        }
        resres.sc = inverseCS;
        return resres;
    }

    //Decodes given encoded string and encoding to character table
    static String decode (EnRes resres){
        StringBuilder result = new StringBuilder();
        String iter = resres.encodedString;
        HashMap<String, Character> sc = resres.sc;
        int len = iter.length();
        String token = "";
        for(int i = 0; i < len; i++){
            token+=iter.charAt(i);
            if(sc.containsKey(token)){
                result.append(sc.get(token));
                token = "";
            }
        }
        return result.toString();
    }

    //Gets new encoding tree as a root Node given string to be encoded
    static Node getNewEncodeTree(String tbe){
        HashMap<Character,Integer> x = new HashMap<>();
        int len = tbe.length();
        PriorityQueue<CharFreq> y = new PriorityQueue<>(new CharFreqComparator());
        for(int i = 0; i < len; i++){ //Finding frequencies of each character
            Character curr = tbe.charAt(i);
            if(!x.containsKey(curr)){
                x.put(curr,1);
            }
            else{
                x.put(curr,x.get(curr)+1);
            }
        }
        //Making CharFreqs adding to PQ
        //that returns lowest frequency CharFreq first
        for(Character r : x.keySet()){
            CharFreq temp = new CharFreq();
            temp.c = r;
            temp.i = x.get(r);
            temp.subtree = null;
            y.add(temp);
        }
        Node root = new Node();
        while(!y.isEmpty()) {
            CharFreq temp = y.poll();
            //PQ will pull Highest Freq CharFreq last (which should be final root)
            if(!y.isEmpty()){ //if haven't encountered final root
                CharFreq temp1 = y.poll();
                Node temp2 = new Node();
                Node child1 = new Node();
                if(temp.subtree == null) { //if first child of pair is a leaf
                    child1.c = temp.c;
                    child1.SubTreeFreq = temp.i;
                    child1.left = null;
                    child1.right = null;
                }
                else{
                    child1.c = null;
                    child1.SubTreeFreq = temp.subtree.SubTreeFreq;
                    child1.left = temp.subtree.left;
                    child1.right = temp.subtree.right;
                }
                Node child2 = new Node();
                if(temp1.subtree == null) { //if second child of pair is a leaf
                    child2.c = temp1.c;
                    child2.SubTreeFreq = temp1.i;
                    child2.left = null;
                    child2.right = null;
                }
                else{
                    child2.c = null;
                    child2.SubTreeFreq = temp1.subtree.SubTreeFreq;
                    child2.left = temp1.subtree.left;
                    child2.right = temp1.subtree.right;
                }
                temp2.left = child1; //temp2 is Node representing the parent of temp and temp1
                temp2.right = child2;
                temp2.SubTreeFreq = temp.i+temp1.i;
                temp2.c = null;
                CharFreq adder = new CharFreq(); //adder is CharFreq representation of temp2 to put in PQ
                adder.c = null;
                adder.i = temp2.SubTreeFreq;
                adder.subtree = temp2;
                /*Add parent from pairing of
                two lowest freq CharFreqs
                and create their respective nodes*/
                y.add(adder);
                /*Repeatedly update root
                depending on which node
                is furthest up so far*/
                root.left = temp2.left;
                root.right = temp2.right;
                root.c = temp2.c;
                root.SubTreeFreq = temp2.SubTreeFreq;
            }
            else{ //encountered final root
                root.left = temp.subtree.left;
                root.right = temp.subtree.right;
                root.c = temp.subtree.c;
                root.SubTreeFreq = temp.subtree.SubTreeFreq;
                break;
            }
        }
        return root;
    }
    static class CharFreq{
        Character c; //Character (if leaf)
        Integer i; //Frequency (literal freq if leaf, sum of children if intermediate)
        Node subtree; //subtree if not leaf
    }
    static class Node {
        Node left; //left child
        Node right; //right child
        Integer SubTreeFreq; //sum of SubTreeFreqs of children (unless leaf in which case literal freq)
        boolean visited = false; //check visited during bfs
        Character c; //only for leaf
        String path = ""; //represents left-right path to Node from root in terms of 0s and 1s
    }

    //Comparator that sorts by lowest frequency
    static class CharFreqComparator implements Comparator<CharFreq>{
        @Override
        public int compare(CharFreq o1, CharFreq o2) {
            return (o1.i <= o2.i) ? -1 : 1;

        }
    }

    //EncodedResult
    static class EnRes{
        String encodedString;
        HashMap<String,Character> sc;
    }
}
