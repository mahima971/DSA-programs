#include<iostream>
#include<vector>
using namespace std;

void get_string_perumation(string str,string psf, vector<string>&ans){
    if(str.length()==0){
        ans.push_back(psf);

    }
    for(int i=0;i<str.length();i++){
        char ch=str[i];
        string ros = str.substr(0,i)+ str.substr(i+1);
        get_string_perumation(ros,psf+ch, ans);
    }
}
int main(){
    string str="abcde";
    vector<string>ans;
    get_string_perumation(str, "", ans);
    return 0;
}