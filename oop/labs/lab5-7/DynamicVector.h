#ifndef FT_DYNAMIC_VECTOR_H
# define FT_DYNAMIC_VECTOR_H

template <typename T>
class DynamicVector
{
	private:
		T*		elems;
		int		size;
		int		capacity;

		// Resize the DynamicVector. It's capacity is multiplyed by a given factor.
		void	resize(double factor = 2);

	public:
		// Constructor (default)
		DynamicVector(int capacity = 10);

		// Creates a copy of the DynamicVector
		DynamicVector(const DynamicVector& v);

		// Destructor
		~DynamicVector();

		// @Overrides the assignment operator
		DynamicVector& operator=(const DynamicVector& v);

		// Overloads the subscript operator and returns a reference to the element at the specified position
		T& operator[](int pos);

		// Adds a new element to the DynamicVector.
		void add(T e);

		// Removes an element from the DynamicVector from the specified position.
		void remove(int pos);

		// Returns the current size of the DynamicVector
		int getSize() const;

};

template <typename T>
DynamicVector<T>::DynamicVector(int capacity = 10) {
	this->size = 0;
	this->capacity = capacity;
	this->elems = new T[capacity];
}

template <typename T>
DynamicVector<T>::DynamicVector(const DynamicVector<T>& v) {
	this->size = v.size;
	this->capacity = v.capacity;
	this->elems = new T[this->capacity];
	for (int i = 0; i < this->size; i++)
		this->elems[i] = v.elems[i];
}

template <typename T>
DynamicVector<T>::~DynamicVector() {
	delete[] this->elems;
}

template <typename T>
DynamicVector<T>&	DynamicVector<T>::operator=(const DynamicVector<T>& v) {
	if (this == &v)
		return *this;

	this->size = v.size;
	this->capacity = v.capacity;

	delete[] this->elems;
	this->elems = new T[this->capacity];
	for (int i = 0; i < this->size; i++)
		this->elems[i] = v.elems[i];

	return *this;
}

template <typename T>
T&		DynamicVector<T>::operator[](int pos) {
	return this->elems[pos];
}

template <typename T>
void	DynamicVector<t>::remove(int pos) {
	if (pos >= size) {
		return;
	}
	if (size == 1) {
		size--;
		return;
	}
	for (int i = pos; i < size - 1; ++i) {
		elems[i] = elems[i + 1];
	}
	size--;
}

template <typename T>
void	DynamicVector<T>::add(T e) {
	if (this->size == this->capacity)
		this->resize();
	this->elems[this->size] = e;
	this->size++;
}

template <typename T>
int		DynamicVector<T>::getSize() const {
	return this->size;
}

template <typename T>
void	DynamicVector<T>::resize(double factor) {
	this->capacity = (int)(this->capacity * factor);

	T* els = new T[this->capacity];
	for (int i = 0; i < this->size; i++)
		this->elems[i] = els[i];

	delete[] this->elems;
	elems = els;
}

#endif
