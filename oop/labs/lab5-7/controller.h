#ifndef FT_CONTROLLER_H
# define FT_CONTROLLER_H

# include "master_cpp.h"
# include "repository.h"

class	Controller
{
	private:
		Repository	repo;
	public:
		Controller(const Repository& repository) : repo(repository) {}
		/*
		 *	Description: Adds a new tutorial to the tutorial list
		 */
		int			addTutorial(Tutorial tutorial);
		/*
		 *	Description: Adds a new tutorial to the watch list
		 */
		int			addToWatchList(Tutorial tutorial);
		/*
		 *	Description: Returns a list of all the tutorials in the DB
		 */
		vector<Tutorial>	getAll(void);
		/*
		 *	Description: Returns a list of all the tutorials in the watch list
		 */
		vector<Tutorial>	getWatchList(void);
		Tutorial	*getOneByTitle(string title);
		/*
		 *	Description: Removes the tutorial with the given title from the tutorial list 
		 */
		int			removeTutorial(string title);
		int			removeFromWatchList(string title);
		/*
		 *	Description: Adds a like to the tutorial with the given title
		 */
		void		likeTutorial(string title);
		int			updateTutorial(Tutorial tutorial);
		vector<Tutorial>	getByPresenter(string presenter);
		/*
		 *	Description: Stores the current state of the tutorial list to a CSV file
		 */
		void		storeToFile(void);
		void		storeWatchToFile(void);
		/*
		 *	Description: Reads the tutorial list from a CSV file
		 */
		void		getFromFile(void);
		void		getWatchFromFile(void);
		void		openWatchOnBrowser();
};

#endif
