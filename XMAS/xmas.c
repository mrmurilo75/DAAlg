#include<stdio.h>
char s[28][30];
int i=28,j=30,a=13,b=13,S,E,c='o';
void p(){if(!(c-42))s[S][a]=s[S][b]='o';for(;S++<E;a--,b++)s[S][a]=s[S][b]=c;a=b=13;}
void f(int a){for(;(a+=2)<E;)s[S][a]='o';}
int main(){
  for(;i--;j=30)
    while(j--)s[i][j]=95;
  for(i=28;i--;)s[i][29]=10;
  p(S=0,E=7);
  p(S=4,E=13);
  p(S=9,E=21);
  S=21;E=25;f(0);
  E=15;
  while(S++<27)f(10);
  s[1][13]=42;s[27][13]='o';
  p(S=-1,E=8,c=42);
  S=7;E=13;p(a=9,b=17);
  S=E;E=21;p(a=8,b=18);
  s[27][29]=0;
  puts(&s[0][0]);
}
