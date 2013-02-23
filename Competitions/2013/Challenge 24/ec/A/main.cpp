#include <iostream>
#include <vector>
#include <cstdio>
#include <queue>
using namespace std;

vector<int> podredjeni[3000000];

vector<int> niz_dubina[3000000];

int dubina_c[3000000];
int brNoMng[3000000];

int maxDubina = 0;

void dfs(int cvor, int dubina) {
    dubina_c[cvor] = dubina;
    maxDubina = maxDubina < dubina ? dubina : maxDubina;
    for( int i = 0; i < podredjeni[cvor].size(); i++  ) {
        dfs(podredjeni[cvor][i], dubina + 1);
    }
}

void obrisiOvogIDodajNjegove(int cvor,int  ovaj) {
    brNoMng[cvor] += brNoMng[ovaj];

    for(int i = 0; i < podredjeni[cvor].size(); i++) {
       if (ovaj == podredjeni[cvor][i]) {
        podredjeni[cvor].erase(podredjeni[cvor].begin() + i);
        break;
       }
    }
    for(int i = 0; i < podredjeni[ovaj].size(); i++) {
        podredjeni[cvor].push_back(podredjeni[ovaj][i]);
    }

}
int main()
{
    int sol = 0;
    int n, m;

    freopen("input.txt", "r", stdin);
    scanf("%d", &n);
    scanf("%d", &m);

    for( int i = 0; i < n; i++ ) {
        int a;
        scanf("%d", &brNoMng[i]);
        scanf("%d", &a);
        for( int j = 0; j < a; j++) {
            int b;
            scanf("%d", &b);
            podredjeni[i].push_back(b);
        }
    }

    dfs(0, 0);
    int maxx = 0;
    for(int i = 0; i < 3000000; i++)
      maxx = max(maxx, dubina_c[i]);
    // ovde imamo nivoe
    for(int i = 0; i < n; i++) {
        printf("%d\n", i);
        niz_dubina[dubina_c[i]].push_back(i);
    }
    // niz_dubina[dubina][cvor]
    for( int  i = maxDubina - 1; i >= 0; i--) {
        printf("%d\n", i);

        for( int cvorI = 0; cvorI < niz_dubina[i].size(); cvorI++ ) {

            int cvor = niz_dubina[i][cvorI];
            priority_queue< pair<int, pair<int, vector<int>::iterator > > , vector< pair<int, pair<int, vector<int>::iterator > > >  , greater<pair<int, pair<int, vector<int>::iterator > > > > hip;
            for(int j = 0; j < podredjeni[cvor].size(); j++) {
                int podr = podredjeni[cvor][j];

                hip.push(make_pair(podredjeni[podr].size() + brNoMng[podr], make_pair(podr,podredjeni[cvor].begin() + j) ));
            }
            while( !hip.empty() ) {
                pair<int, pair<int, vector<int>::iterator > >  top = hip.top();
                hip.pop();

                int c = -1;
                int  a = top.first + podredjeni[cvor].size() + brNoMng[cvor] + c;
                if( a > m )
                    break;

                obrisiOvogIDodajNjegove(cvor, top.second.first);
                //printf("%d\n", top.second.first);
                sol++;
            }

        }

    }

    printf("%d\n", sol);

    return 0;
}
