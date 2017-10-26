// Computes the sum of n real numbers

int main()
{
	int n;
	int nbr;
	int s;
	int i;
	
	cin >> n;

	s = 0;
	i = 0;
	while (i < n) {
		cin >> nbr;
		s = s + nbr;
		i = i + 1;
	}

	cout << s;

	return 0;
}
