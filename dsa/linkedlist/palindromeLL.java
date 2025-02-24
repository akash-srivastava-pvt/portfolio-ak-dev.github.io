public boolean isPalindrome() {
    if (head == null || head.next == null) return true;

    // Find middle
    Node slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }

    // Reverse second half
    Node prev = null, current = slow, next;
    while (current != null) {
        next = current.next;
        current.next = prev;
        prev = current;
        current = next;
    }

    // Compare first and second halves
    Node first = head, second = prev;
    while (second != null) {
        if (first.data != second.data) return false;
        first = first.next;
        second = second.next;
    }
    return true;
}
