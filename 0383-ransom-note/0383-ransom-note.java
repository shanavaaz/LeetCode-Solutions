class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character,Integer> map=new HashMap<>();
        HashMap<Character,Integer> map2=new HashMap<>();
        char[] ch=ransomNote.toCharArray();
        char[] ch2=magazine.toCharArray();
        for(char char1:ch){
            map.put(char1,map.getOrDefault(char1,0)+1);
        }
        for(char char2:ch2){
            map2.put(char2,map2.getOrDefault(char2,0)+1);
        }
        for(char c:map.keySet()){
            if(map.get(c)>map2.getOrDefault(c,0)){
                return false;
            }
        }
        return true;

        
    }
}