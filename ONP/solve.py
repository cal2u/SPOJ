"""
    Spoj Problem ONP, "ONP - Transform the Expression"
    Solved by cal2u 9/11/2015
"""

import string
operators = ["+","-","*","/","^","(",")"]

t = input()

def process_string(str): 
    stack = []
    ans = ""
    sub_expr = []

    for char in str:
        if char in string.ascii_lowercase:
            ans += char
        elif char == "(":
            stack.append(char)
        elif char == ")":
            while stack[-1] != "(":
                ans += stack.pop()
            stack.pop()
        elif char in operators:
            while len(stack) and operators.index(stack[-1]) <= operators.index(char):
                ans += stack.pop()
            stack.append(char)
    return ans

while t:
    print process_string(raw_input())
    t -= 1

