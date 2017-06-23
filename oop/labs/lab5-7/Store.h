#pragma once

# include "master_cpp.h"

class	Store
{
	protected:
		vector<Tutorial> tutorials;

	public:
		Store() {}

		// Writes all the watch list in a CSV file
		void	storeAll(vector<Tutorial> v);
};
