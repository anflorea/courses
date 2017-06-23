#ifndef FT_REPOSITORY_H
# define FT_REPOSITORY_H

# include "master_cpp.h"
# include "domain.h"

class	Repository
{
	private:
		vector<Tutorial>	tutorialList;

	public:
		Repository();
		void	addTutorial(Tutorial tutorial);
		vector<Tutorial>	getAll(void);
		Tutorial	*getOneByTitle(string title);
		int			getPositionByTitle(string title);
		void	removeTutorial(int position);
		void	updateTutorial(int position, Tutorial tutorial);
};

#endif
