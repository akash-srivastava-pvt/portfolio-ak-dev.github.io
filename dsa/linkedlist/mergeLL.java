public static Node mergeSortedLists(Node l1, Node l2) {
    if (l1 == null) return l2;
    if (l2 == null) return l1;

    if (l1.data < l2.data) {
        l1.next = mergeSortedLists(l1.next, l2);
        return l1;
    } else {
        l2.next = mergeSortedLists(l1, l2.next);
        return l2;
    }
}
