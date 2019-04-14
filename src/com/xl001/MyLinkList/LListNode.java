package com.xl001.MyLinkList;

import javax.swing.*;

public class LListNode {

    private static class ListNode {
        private int val;
        ListNode next;
        ListNode random;
        ListNode(int x){
            val = x;
        }
    }


    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;
        n2.next =null;


    }
    /*
    头插方法
     */
    public ListNode reverseList(ListNode head){
        //创建一个新的链表存放逆置后的链表
        //遍历原来链表的每一个结点
        //把结点头插到新的空链表
        //返回新链表
        ListNode result = null;
        ListNode cur = head;

        while(cur != null){
            //防止node的next被修改，先放一个变量记录node的next结点
            ListNode next = cur.next;
            //头插
            cur.next = result;
            result = cur;

            cur = next;
        }
        return result;
    }
    /*
    指针方法
     */
    public ListNode reverse(ListNode head){
        ListNode p1 =head;
        ListNode p2 =head.next;
        while (p2!=null){
            ListNode p3 =p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = p3;
        }
        return p2;
    }

    public ListNode pai(ListNode head, int x){
        ListNode small = null;
        ListNode smallLast = null;
        ListNode big = null;
        ListNode bigLast = null;
        ListNode next = null;
        ListNode cur = head;
        while(cur!=null){
            next = cur.next;
            if(cur.val < x){
                cur.next = null;
                if(small == null){
                    small = cur;
                }else{
                    smallLast.next = cur;
                }
                smallLast = cur;
                cur = next;
            }else{
                if(big == null){
                    big = cur;
                }else{
                    bigLast.next = cur;
                }
                bigLast = cur;
                cur = next;
            }
        }
        //考虑其中有链表为空的情况
        if(small == null){
            return big;
        }else{
            smallLast.next = big;
            return small;
        }
    }

    public ListNode deleteDuplication(ListNode pHead) {
        ListNode dummy = new ListNode(0);
        dummy.next = pHead;
        ListNode pre = dummy;
        ListNode p1 = pHead;
        ListNode p2 = pHead.next;

        while(p2 != null){
            if(p1 != p2){
                pre = pre.next;
                p1 = p1.next;
                p2 = p2.next;
            }else{
                p2= p2.next;
            }
            pre.next = p2;
            p1 = p2;
            p2 = p2.next;

        }
       return  p1;
    }

    public ListNode merge(ListNode l1, ListNode l2){
        if(l1==null){
            return l1;
        }
        if(l2==null){
            return l2;
        }
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode result = null;
        ListNode last = null;
        while(cur1!=null&&cur2!=null){
            if(cur1.val <= cur2.val){
                ListNode next = cur1.next;
                if(result == null){
                    result = cur1;
                }else{
                     last.next = cur1;
                }
                last = cur1;
                cur1 = next;
            }else{
                ListNode next = cur2.next;
                if(result == null){
                    result = cur2;
                }else{
                    last.next = cur2;
                }
                last = cur2;
                cur2 = next;
            }
        }
        if(cur1 == null){
            last.next = cur2;
        }
        if(cur2 == null){
            last.next = cur1;
        }
        return result;
    }
    public ListNode getLast(ListNode head){
        ListNode cur = head;
        while(cur !=null){
            cur= cur.next;
        }
        return cur;
    }
    public boolean isCross(ListNode head1, ListNode head2){
        //判断链表是否相交
        ListNode c1 = head1;
        ListNode c2 = head2;
        if(getLast(c1)==getLast(c2)){
            return true;
        }
        return false;
    }
    public int getLength(ListNode head){
        int count =0;
        for(ListNode cur=head; cur!=null; cur=cur.next){
            count++;
        }
        return count;
    }

    public ListNode getIntersectionNode(ListNode head1, ListNode head2){
        /*
        寻找相交链表的相交结点
        */
        int lenA = getLength(head1);
        int lenB = getLength(head2);
        ListNode longer = head1;
        ListNode shoter = head2;
        int diff = lenA-lenB;
        if(diff<0){
            diff = lenB-lenA;
            longer = head2;
            shoter = head1;
        }
        for(int i=0; i<diff;i++){
            longer = longer.next;
        }
        while (longer==shoter){
            longer = longer.next;
            shoter = shoter.next;
        }
        return longer;
    }


    /*
    复杂链表的复制
    1.遍历原链表的每一个结点，复制新结点，查到原来的后边
    2.遍历原来的每一个结点，进行新结点 random的设置
    3.把一条链表拆分为原来链表和新链表
     */
    public ListNode copyRandomList(ListNode head){
        //复制每一个结点插在原来结点的后面
        ListNode cur = head;
        while(cur!=null){
            ListNode newNode = new ListNode(cur.val);
            newNode.next = cur.next;
            cur.next =newNode;
            cur = newNode.next;
        }

        //复制原来链表的random
        cur = head;
        while(cur!=null){
            ListNode newNode = cur.next;
            if(cur.random==null){
                newNode.random = null;
            }else {
                newNode.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        cur = head;
        //记录复制链表的头结点
        ListNode nNode = cur.next;
        while(cur!=null){
            ListNode newNode = cur.next;
            cur.next = newNode.next;
            if(newNode.next!=null){
                newNode.next = newNode.next.next;
            }
           cur = cur.next;
        }
        return nNode;
    }
}
