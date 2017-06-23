#include "Html.h"

void Html::storeAll(vector<Tutorial> v)
{
    this->tutorials = v;
    string line = "";
    ofstream file;
	file.open("TutorialsHtml.html");
    file << "<!DOCTYPE html>" << endl << "<html>" << endl;
    file << "\t<head>" << endl << "\t\t<title>Tutorials list</title>" << endl;
    file << "\t</head>" << endl << "\t<body>" << endl << "\t\t<table border=\"1\"" << endl<<"\t\t<tr>" << endl;
    file << "\t\t\t<td>Title</td>" << endl << "\t\t\t<td>Presenter</td>" << endl << "\t\t\t<td>Duration</td>" << endl << "\t\t\t<td>Likes</td>" << endl << "\t\t\t<td>Link</td>" << endl;
    file << "\t\t</tr>" << endl;
    for(unsigned int i = 0; i < this->tutorials.size(); i++)
    {
        Tutorial t = this->tutorials[i];
        file << "\t\t<tr>" << endl;
        file << "\t\t\t<td>" << t.getTitle() << "</td>" << endl << "\t\t\t<td>" << t.getPresenter() << "</td>" << endl << "\t\t\t<td>" << t.getDuration() << "</td>"<< endl << "\t\t\t<td>" << t.getLikes() << "</td>" << endl << "\t\t\t<td><a href =\"" << t.getLink() << "\">Link</a></td></td>" << endl;
        file << "\t\t</tr>" << endl;
    }
    file << "\t\t</table>" << endl << "\t</body>" << endl << "</html>" << endl;
    file.close();
}
