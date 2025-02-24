public Node findNthFromEnd(int n) {
    Node first = head, second = head;
    for (int i = 0; i < n; i++) {
        if (first == null) return null;
        first = first.next;
    }
    while (first != null) {
        first = first.next;
        second = second.next;
    }
    return second;
}
