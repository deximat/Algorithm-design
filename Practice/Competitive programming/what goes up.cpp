#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class LIS {
    int tren;
    vector<int> pred;
    vector<int> niz;
    public:
        void add(int x) {
            niz.push_back(x);
            vector<int>::iterator iter = upper_bound(niz.begin(),niz.end(), x);
            tren++;
        }
};
vector<int> p;

int main()
{
    int brojevi[] = {10,20,30};
    vector<int> c(brojevi, brojevi+3);
    cout << (c.end() == upper_bound(c.begin(), c.end(), 30));

    return 0;
}
