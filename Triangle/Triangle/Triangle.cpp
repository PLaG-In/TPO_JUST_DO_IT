// Triangle.cpp: определяет точку входа для консольного приложения.
//

#include "stdafx.h"

bool IsItCorrectNumber(string &num)
{
	int point = 0, i = 0, minus = 0;
	if (num.length() > 0)
	{
		for (size_t i = 0; i < num.length(); i++)
		{
			if (isdigit(num[i]) || num[i] == '.' || num[i] == '-')
			{
				if (num[i] == '.')
				{
					if (point == 0 && i != 0 && isdigit(num[i - 1]))
					{
						point++;
					}
					else
					{
						return false;
					}
				}
				if (num[i] == '-')
				{
					if (minus == 0 && i == 0)
					{
						minus++;
					}
				}
			}
			else
			{
				return false;
			}
		}
		return true;
	}
	else
	{
		return false;
	}
}

//bool PythagoreanTheorem(double side1, double side2, double side3)
//{
//	if ((pow(side1, 2) + pow(side2, 2)) == pow(side3, 2)
//		|| (pow(side1, 2) + pow(side3, 2)) == pow(side2, 2)
//		|| (pow(side3, 2) + pow(side2, 2)) == pow(side1, 2))
//	{
//		return true;
//	}
//	return false;
//}

double CountUpLengthOfSides(double x, double x1, double y, double y1)
{
	double a;
	a = sqrt(pow((x1 - x), 2) + pow((y1 - y), 2));
	return int(a * 1000 + 0.5) / 1000.0;
}

void DefineTriangle(vector<pair<double, double>> coords)
{
	double len1, len2, len3;
	if ((coords[0].first == coords[1].first && coords[0].second == coords[1].second)
		|| (coords[0].first == coords[2].first && coords[0].second == coords[2].second)
		|| (coords[1].first == coords[2].first && coords[1].second == coords[2].second))
	{
		cout << "Not triangle\n";
		return;
	}
	len1 = CountUpLengthOfSides(coords[0].first, coords[1].first, coords[0].second, coords[1].second);
	len2 = CountUpLengthOfSides(coords[0].first, coords[2].first, coords[0].second, coords[2].second);
	len3 = CountUpLengthOfSides(coords[1].first, coords[2].first, coords[1].second, coords[2].second);
	cout << len1 << " " << len2 << " " << len3 << endl;
	if (len1 >= len2 + len3 || len2 >= len1 + len3 || len3 >= len2 + len1)
	{
		cout << "Not triangle\n";
	}
	else
	{
		if (len1 == len2 && len1 == len3)
		{
			cout << "Equilateral\n";
			return;
		}
		if ((len1 == len2) || (len2 == len3) || (len1 == len3))
		{
			cout << "Isosceles\n";
			return;
		}
		/*if (PythagoreanTheorem(len1, len2, len3))
		{
			cout << "Right\n";
			return;
		}*/
		else
		{
			cout << "Usual\n";
		}
	}
}

int main(int argc, char* argv[])
{
	if (argc != 4)
	{
		printf("Error!Incorrect number of parameters.\n");
		return 1;
	}
	char *next_token1 = NULL;
	string pch, pch2;
	vector<pair<double, double>> coords;
	exception e;
	for (size_t i = 1; i < 4; ++i)
	{
		try
		{
			if (strlen(argv[i]) < 3)
			{
				throw e;
			}
			pch = strtok_s(argv[i], ",", &next_token1);
			pch2 = next_token1;
			if (!IsItCorrectNumber(pch) || !IsItCorrectNumber(pch2))
			{
				throw e;
			}
			stod(pch);
			stod(pch2);
		}
		catch (exception &e)
		{
			cout << "Error!Wrong parameters.\n";
			return 1;
		}
		pair<double, double> xy = make_pair(stod(pch), stod(pch2));
		coords.push_back(xy);
	}
	DefineTriangle(coords);
	return 0;
}

