/*
    Spoj.com Problem PARTY 
    Solved by cal2u on 10/12/15
*/

#include <stdio.h>
#include <math.h>

const int MAX_BUDGET = 500;
const int MAX_PARTIES = 100;

int budget =-1, n = -1;
int memo[MAX_PARTIES+1][MAX_BUDGET+1];// max fun considering up to [party][money_left]

int costs[MAX_PARTIES+1];
int funness[MAX_PARTIES+1];


// Trying a bottom-up DP approach this time
void memoize(int index) {
    for (int money_left = 0; money_left <= budget; money_left++) {
        if (index == 0) memo[index][money_left] = 0;
        else {
            // The best ans will be the max of whether we go to the party or not
            // (if have money to even go)
            memo[index][money_left] = (money_left >= costs[index]) ? 
                (int)fmax(
                    memo[index-1][money_left], 
                    funness[index] + memo[index-1][money_left-costs[index]]
                )
            : memo[index-1][money_left];
        }
    }
}


int main(int argc, char ** argv) {
    scanf("%d %d", &budget, &n);
    memoize(0);
 
    // Do test cases while we have 'em
    while(budget !=0 && n!=0) { 

        for (int i = 1; i <= n; i++) {
            scanf("%d %d", &costs[i], &funness[i]);
            memoize(i);
        }
        
        int min_budget = budget;
        int max_fun = memo[n][budget];
        while (memo[n][min_budget-1] == max_fun) min_budget--; 
        printf("%d %d\n", min_budget,max_fun);
        scanf("%d %d", &budget, &n);
    }

    return 0;
}
