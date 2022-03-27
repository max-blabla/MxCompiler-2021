class A{
    int a;
    int b = 0;
    int c[100] = {0};
    int* d;
public:
    void foo1(){
        d = new int[12];
    }
    int foo2(int s){
        return a + s;
    }
};
A a;
int main(){
    A s;
    s = a;
    s.foo1();
    s.foo2(2);
    A * ss;
    ss = new A[123];
}