class Solution {
    public List<List<Integer>> combinationSum2(int[] cand, int target) {
        cand = mergesort(cand);
        List<List<Integer>> ans = new ArrayList<>();
        fun(0,cand,new ArrayList<>(),target,ans);
        List<List<Integer>> list = new ArrayList<>(ans);
        return list;
    }
    static void fun(int index,int[] can,List<Integer> curr,int target,List<List<Integer>> ans){
        if(target==0){
            ans.add(new ArrayList<>(curr));
        }
        for(int i= index;i<can.length;i++){
            if(i>index && can[i]==can[i-1]) continue;
            if(can[i] > target) break;
            curr.add(can[i]);
            fun(i+1,can,curr,target-can[i],ans);
            curr.remove(curr.size()-1);
        }
    }
    static int[] mergesort(int[] arr){
        if(arr.length<=1){
            return arr;
        }
        int mid = arr.length/2;
        int left[] = mergesort(Arrays.copyOfRange(arr,0,mid));
        int right[] = mergesort(Arrays.copyOfRange(arr,mid,arr.length));
        return merge(left,right);
    }
    static int[] merge(int[] left,int[] right){
        int ans[] = new int[left.length+right.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < left.length && j < right.length){
            if(left[i] < right[j]){
                ans[k++] = left[i++];
            }else{
                ans[k++] = right[j++];
            }
        }
        while(i < left.length){
            ans[k++] = left[i++];
        }
        while(j < right.length){
            ans[k++] = right[j++];
        }
        return ans;
    }
}