/* 
 * Spoj.com Problem Hexgram
 * Solved by cal2u on 10/21/15
*/
#include <cstdio>
#include <iostream>

int ans = 0;
int row_sum;
int nums[12];
int star[12];
bool taken[12];

// takes in the number already filled
void fill_star(int num_filled)
{
	if (star[1] != 0 && star[2] != 0 && star[3] != 0 && star[4] != 0 
		&& star[1]+star[2]+star[3]+star[4] != row_sum) return;
	if (star[1] != 0 && star[5] != 0 && star[8] != 0 && star[11] != 0
		&& star[1]+star[5]+star[8]+star[11] != row_sum) return;
	if (star[4] != 0 && star[6] != 0 && star[9] != 0 && star[11] != 0
	 	&& star[4]+star[6]+star[9]+star[11] != row_sum) return;

	if (star[0] != 0 && star[2] != 0 && star[5] != 0 && star[7] != 0 
		&& star[0]+star[2]+star[5]+star[7] != row_sum) return;
	if (star[0] != 0 && star[3] != 0 && star[6] != 0 && star[10] != 0 
		&& star[0]+star[3]+star[6]+star[10] != row_sum) return;
	if (star[7] != 0 && star[8] != 0 && star[9] != 0 && star[10] != 0
		&& star[7]+star[8]+star[9]+star[10] != row_sum) return;

	if (num_filled == 12) ans++; return;
	for (int i = 1; i < 12; i++)
	{
		if (!taken[i])
		{
			star[num_filled] = nums[i];
			taken[i] = true;
			fill_star(num_filled+1);
			taken[i] = false;
			star[num_filled] = 0;
		}
	}
}

int main(int argc, char ** argv)
{
	while (1)
	{
		ans = 0;
		int sum = 0;
		for (int i = 0; i < 12; i++)
		{
			std::cin >> nums[i];
			sum += nums[i];

			if (nums[i] == 0)
				return 0;
		}

		row_sum = sum/3;
		for (int i = 0; i < 12; i++) {
			star[i] = 0;
		}
		star[0] = nums[0];
		taken[0] = true;

		fill_star(1);

		printf("%d\n", ans);
	}
}
