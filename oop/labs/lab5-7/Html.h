#pragma once
#include "master_cpp.h"
#include "Store.h"

class Html: public Store
{
public:
    Html() {}
    ~Html() {}

	// Stores all the watch list in an html file
    void storeAll(vector<Tutorial> v);
    void open();
};
