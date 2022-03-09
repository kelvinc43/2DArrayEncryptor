public class Encryptor
{
  /** A two-dimensional array of single-character strings, instantiated in the constructor */
  private String[][] letterBlock;

  /** The number of rows of letterBlock, set by the constructor */
  private int numRows;

  /** The number of columns of letterBlock, set by the constructor */
  private int numCols;

  /** Constructor*/
  public Encryptor(int r, int c)
  {
    letterBlock = new String[r][c];
    numRows = r;
    numCols = c;
  }
  
  public String[][] getLetterBlock()
  {
    return letterBlock;
  }
  
  /** Places a string into letterBlock in row-major order.
   *
   *   @param str  the string to be processed
   *
   *   Postcondition:
   *     if str.length() < numRows * numCols, "A" in each unfilled cell
   *     if str.length() > numRows * numCols, trailing characters are ignored
   */
  public void fillBlock(String str)
  {
    int k = 0;
    for (int i = 0; i < letterBlock.length; i++) {
      for (int a = 0; a < letterBlock[0].length; a++) {
        if (k < str.length()) {
          letterBlock[i][a] = str.substring(k, k + 1);
          k++;
        } else letterBlock[i][a] = "A";
      }
    }
  }

  /** Extracts encrypted string from letterBlock in column-major order.
   *
   *   Precondition: letterBlock has been filled
   *
   *   @return the encrypted string from letterBlock
   */
  public String encryptBlock()
  {
    String a = "";
    for (int col = 0; col < letterBlock[0].length; col++) {
      for (int row = 0; row < letterBlock.length; row++) {
        a += letterBlock[row][col];
      }
    }
    return a;
  }

  /** Encrypts a message.
   *
   *  @param message the string to be encrypted
   *
   *  @return the encrypted message; if message is the empty string, returns the empty string
   */
  public String encryptMessage(String message)
  {
    String a = "";
    int loops = message.length() / (numRows * numCols);
    int k = numRows * numCols;
    int temp = 0;
    for (int i = 0; i < loops; i++) {
      fillBlock(message.substring(k * i, (i+1) * k));
      a += encryptBlock();
      temp = i;
    }
    if (message.length() % (numRows * numCols) != 0) {
      fillBlock(message.substring((temp + 1) * k));
      a += encryptBlock();
    }
    return a;
  }

  /**  Decrypts an encrypted message. All filler 'A's that may have been
   *   added during encryption will be removed, so this assumes that the
   *   original message (BEFORE it was encrypted) did NOT end in a capital A!
   *
   *   NOTE! When you are decrypting an encrypted message,
   *         be sure that you have initialized your Encryptor object
   *         with the same row/column used to encrypted the message! (i.e.
   *         the “encryption key” that is necessary for successful decryption)
   *         This is outlined in the precondition below.
   *
   *   Precondition: the Encryptor object being used for decryption has been
   *                 initialized with the same number of rows and columns
   *                 as was used for the Encryptor object used for encryption.
   *
   *   @param encryptedMessage  the encrypted message to decrypt
   *
   *   @return  the decrypted, original message (which had been encrypted)
   *
   *   TIP: You are encouraged to create other helper methods as you see fit
   *        (e.g. a method to decrypt each section of the decrypted message,
   *         similar to how encryptBlock was used)
   */
  public String decryptMessage(String encryptedMessage)
  {
    int num = numCols * numRows;
    int a = encryptedMessage.length() / num;
  }
}