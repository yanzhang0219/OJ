/*
 * [929] Unique Email Addresses
 *
 * https://leetcode.com/problems/unique-email-addresses/description/
 *
 * algorithms
 * Easy (79.84%)
 *
 * Every email consists of a local name and a domain name, separated by the @
 * sign.
 * 
 * For example, in alice@leetcode.com, alice is the local name, and
 * leetcode.com is the domain name.
 * 
 * Besides lowercase letters, these emails may contain '.'s or '+'s.
 * 
 * If you add periods ('.') between some characters in the local name part of
 * an email address, mail sent there will be forwarded to the same address
 * without dots in the local name.  For example, "alice.z@leetcode.com" and
 * "alicez@leetcode.com" forward to the same email address.  (Note that this
 * rule does not apply for domain names.)
 * 
 * If you add a plus ('+') in the local name, everything after the first plus
 * sign will be ignored. This allows certain emails to be filtered, for example
 * m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule
 * does not apply for domain names.)
 * 
 * It is possible to use both of these rules at the same time.
 * 
 * Given a list of emails, we send one email to each address in the list.  How
 * many different addresses actually receive mails? 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input:
 * ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
 * Output: 2
 * Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually
 * receive mails
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= emails[i].length <= 100
 * 1 <= emails.length <= 100
 * Each emails[i] contains exactly one '@' character.
 * 
 * 
 */

// Method1: use String built-in methods: split(), replace()
class Solution1 {
  public int numUniqueEmails(String[] emails) {
    if (emails.length == 1) {
      return 1;
    }
    Set<String> unique = new HashSet<>();
    for (String email : emails) {
      String[] parts = email.split("@");
      String[] localSplit = parts[0].split("\\+");
      unique.add(localSplit[0].replace(".", "") + "@" + parts[1]);
    }
    return unique.size();
  }
}

// Method2: without using String built-in methods
class Solution {
  public int numUniqueEmails(String[] emails) {
    Set<String> unique = new HashSet<>();
    for (String email : emails) {
      StringBuilder sb = new StringBuilder();
      boolean meetPlus = false;
      for (int i = 0; i < email.length(); ++i) {
        char c = email.charAt(i);
        if (c != '@') {
          if (!meetPlus) {
            if (c != '.') {
              sb.append(c);
            } 
            if (c == '+') {
              meetPlus = true;
              continue;
            }
          }
        } else {
          // append @ and domain name 
          sb.append(email.substring(i));
          unique.add(sb.toString());
          break;
        }
      }
    }
    return unique.size();
  }
}
