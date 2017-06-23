from console import *
from ui import *
from util import *

def testCompute_insert(data_base, stack):
    compute_insert(['insert 12', '100', 'in', 'desc'], data_base, stack)
    assert data_base[12][0][0] == 100
    assert data_base[12][0][1] == 'in'
    assert data_base[12][0][2] == 'desc'
    remove_elements(1, 31, 'io', data_base, stack)

def testCompute_filter(data_base, stack):
    compute_insert(['insert 12', '100', 'in', 'asdf'], data_base, stack)
    compute_insert(['insert 12', '200', 'out', 'asdf'], data_base, stack)
    compute_filter(['filter in'], data_base, stack)
    assert data_base[12][0][0] == 100
    assert data_base[12][0][1] == 'in'
    assert data_base[12][0][2] == 'asdf'
    compute_filter(['filter out'], data_base, stack)
    assert data_base[12][0][1] == 'eod'

def testCompute_balance(data_base, stack):
    compute_insert(['insert 12', '100', 'in', 'asdf'], data_base, stack)
    compute_insert(['insert 12', '50', 'out', 'asdf'], data_base, stack)
    compute_balance(['balance 12'], data_base, stack)
    assert data_base[12][0][0] == 50

def testCompute_max(data_base, stack):
    compute_insert(['insert 12', '100', 'in', 'asdf'], data_base, stack)
    compute_insert(['insert 12', '50', 'out', 'asdf'], data_base, stack)
    compute_max(['max out day'], data_base, stack)
    assert data_base[12][0][0] == 50
    assert data_base[12][0][1] == 'out'

def testCompute_Undo(data_base, stack):
    compute_remove(['remove 12'], data_base, stack)
    compute_undo(['undo'], data_base, stack)
    assert data_base[12][0][1] == 'eod'
    
def testCompute_Redo(data_base, stack):
    compute_redo(['redo'], data_base, stack)
    assert data_base[12][0][0] == 0

def testCompute_replace(data_base, stack):
    compute_insert(['insert 12', '100', 'in', 'asdf'], data_base, stack)
    compute_insert(['insert 12', '50', 'out', 'asdf'], data_base, stack)
    compute_replace(['replace 12, in, asdf with 200'], data_base, stack)
    assert data_base[12][0][0] == 200


def tests():
    stack = {
            'undo' : [],
            'redo' : [],
            }
    data_base = {}
    i = 1
    while (i <= 31):
        data_base[i] = [[0, 'eod', 'eod']]
        i += 1
    testCompute_insert(data_base, stack)
    testCompute_balance(data_base, stack)
    testCompute_max(data_base, stack)
    testCompute_filter(data_base, stack)
    testCompute_Undo(data_base, stack)
    testCompute_filter(data_base, stack)
    print ("All tests passed successfuly!")

tests()
