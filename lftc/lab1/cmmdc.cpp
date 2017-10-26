// Determine greatest common divisor of two natural numbers

int main()
{
	int firstNumber;
	int secondNumber;

	cin >> firstNumber;
	cin >> secondNumber;

	while (a != b) {
		if (a > b) {
			a = a - b;
		}
		else {
			b = b - a;
		}
	}

	cout << a;

	return 0;
}
