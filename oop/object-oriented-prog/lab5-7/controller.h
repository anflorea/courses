#ifndef FT_CONTROLLER_H
# define FT_CONTROLLER_H

# include "master_cpp.h"
# include "repository.h"

class	Controller
{
	private:
		Repository	repo;
	public:
		Controller(Repository repository) : repo(repository) {}
		int			addTutorial(Tutorial tutorial);
		vector<Tutorial>	getAll(void);
		Tutorial	*getOneByTitle(string title);
		int			removeTutorial(string title);
		int			updateTutorial(Tutorial tutorial);
};

#endif
