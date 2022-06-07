#include<iostream>
#include<vector>
using namespace std;

int get_string_perumation(string str,string psf, vector<string>&ans){
    if(str.length()==0){
        ans.push_back(psf);
        return 1;
    }
    int count=0;
    for(int i=0;i<str.length();i++){
        char ch=str[i];
        string ros = str.substr(0,i)+ str.substr(i+1);
       count+= get_string_perumation(ros,psf+ch, ans);
    }
    return count;
}
int main(){
    string str="abcde";
    vector<string>ans;
    int num;
    
    int count= get_string_perumation(str, "", ans);
    cout<<count<<endl;
    for(string sc:ans){
        cout<<sc<<endl;
    }

    return 0;
}