#include "iostream"
using namespace std;
int foo1(int c){
    return c;
}
void foo2(int c){
    c = 2;
}
void foo3(){
    return;
}
int foo4(){
    return 32;
}
int foo5(int a,int b){
    return a*b+a+b;
}
int foo6(int a,int b,int c,int d,int e,int f,int g,int h,int i,int j,int k,int l){
    return a+b+c+d+e+f+g+h+i+j+k+l;
}

int main(){
    foo1(44);
    foo2(44);
    foo3();
    foo4();
    foo5(32,foo1(1));
    foo6(1,1,1,1,1,1,1,1,1,1,1,1);
    printf("%d",1);
    return 0;
}