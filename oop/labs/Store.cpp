#include "Store.h"

void	Store::storeAll(vector<Tutorial> v)
{
	int			i;
	ofstream	myfile;

	this->tutorials = v;
	myfile.open("watchlist.csv");
	for (i = 0; i < v.size(); i++)
	{
		Tutorial t = v[i];
		myfile << t.getTitle() << ";" << t.getPresenter() << ";" << t.getDuration() << ";" << t.getLikes() << ";" << t.getLink() << endl;
	}
	myfile.close();
}
