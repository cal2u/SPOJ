"""
	Spoj.com Problem PR003004, Digit Sum
	Submitted by cal2u 10/14/15
""" 

# sum the sums <= n
def solve(n):
    if n <= 0: return 0
    if (n+1)%10 == 0: return 45*((n+1)/10)+10*solve(n/10)
    return sum(int(x) for x in str(n))+solve(n-1)

for i in xrange(input()):
    a, b = [int(s) for s in raw_input().split()]
    print solve(b)-solve(a-1)

