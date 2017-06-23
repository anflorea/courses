#include <iostream>
#include <list>
#include <algorithm>

int		gcd(int first, int second)
{
	int		aux;

	while (second != 0)
	{
		aux = second;
		second = first % second;
		first = aux;
	}
	return (first);
}

/*
			  | the first element if the list has one element
			  |
			  | 0 if the list is empty
			  |
			  | gcd(list[0], list[1]) if the list has only two elements	
	gcdList = |
			  |	gcd(list[0], gcdList(list[1:end])) if the list has more than two elements

*/
int		gcdList(std::list<int> theList)
{
	int		aux;

	if (theList.size() == 1)
		return (theList.front());
	if (theList.size() == 0)
		return (0);
	if (theList.size() == 2)
		return (gcd(theList.front(), theList.back()));
	else if (theList.size() > 2)
	{
		aux = theList.front();
		theList.pop_front();
		return (gcd(aux, gcdList(theList)));
	}
}

/*
			   | insert_n(list, solution + number, n - 1, number) if n == 0
			   | solution if the list has no more elements
	insert_n = | insert_n(list[1:n], solution + list[0], n - 1, number) if n != 0

 */

std::list<int>	insert_n(std::list<int> theList, std::list<int> solution, int n, int number)
{
	if (n == 0)
	{
		solution.push_back(number);
		return (insert_n(theList, solution, n - 1, number));
	}
	else if (theList.size() == 0)
		return (solution);
	else
	{
		solution.push_back(theList.front());
		theList.pop_front();
		return (insert_n(theList, solution, n - 1, number));
	}
}

int		main(void)
{
	std::list<int> theList = {5, 10, 25};
	std::list<int> solution;
   	solution = insert_n(theList, solution, 5, 30);

	std::cout << "a -> " << gcdList(theList) << std::endl;
	std::cout << "b -> ";
	while (solution.size())
	{
		std::cout << solution.front() << " ";
		solution.pop_front();
	}
	std::cout << std::endl;
	return (0);
}
