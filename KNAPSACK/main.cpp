/*
    Spoj.com Problem KNAPSACK
    Solved by cal2u on 10/12/15
*/

#include <stdio.h>
#include <math.h>
#include <cstring>

int s, n;
int * memo;
int * sizes;
int * weights;

int solve(int index, int space_left) {
	if (space_left == 0) return 0;
	if (index == n) return 0;
	if (memo[index*(s+1)+space_left] != 0) return memo[index*(s+1)+space_left];

	// if we don't have enough space, return
	if (space_left < sizes[index]) return solve(index+1, space_left);

	int ans = (int)fmax(solve(index+1, space_left), 
					weights[index]+solve(index+1, space_left-sizes[index]));
	memo[index*(s+1)+space_left] = ans;
	return ans;
}

int main(int argc, char ** argv) {

	scanf("%d %d", &s, &n);

	sizes = new int[n];
	weights = new int[n];
	memo = new int[(n)*(s+1)];

	memset(memo, 0, n*s*sizeof(*memo));

	for (int i = 0; i < n; i++) {
		scanf("%d %d", &sizes[i], &weights[i]);
	}
	printf("%d\n", solve(0, s));

	delete sizes;
	delete weights;
	delete memo;

	return 0;
}
