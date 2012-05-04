/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sha;

import java.util.Iterator;
 

/**
 *
 * @author lucian
 */
public class SHABruteForcer extends SHABase implements  Iterable<SHAStep>, Iterator<SHAStep>
{
    protected String hash;
    protected long lastTaskEnd;
    protected long portion;
    protected int workerCount;
    protected long  top ;
    public SHABruteForcer(String hash, int workerCount)
    {
        this.hash = hash;
        lastTaskEnd = 0;
        this.workerCount = workerCount;
        top =(long) Math.pow(ALLOWED_CHARS.length(), MAX_MSG_SIZE);
        portion = (long) Math.pow(ALLOWED_CHARS.length(), MAX_MSG_SIZE);
        portion = (portion/workerCount)/100;
    }

    public SHABruteForcer(String hash, long start, long end)
    {
        this.hash = hash;
        this.lastTaskEnd = start;
        this.top = end;
        portion = (long) Math.pow(ALLOWED_CHARS.length(), MAX_MSG_SIZE);
        portion = (portion/workerCount)/100;
    }   

    public int getWorkerCount()
    {
        return workerCount;
    }

    public void changeWorkerCount(int count)
    {
        this.workerCount+= count;
        portion = (portion/workerCount)/100;
    }

    public Iterator<SHAStep> iterator()
    {
        return this;
    }

    public boolean hasNext()
    {
        return lastTaskEnd<top;
    }

    public SHAStep next()
    {
       SHAStep nxt = new SHAStep(lastTaskEnd+1, lastTaskEnd+portion);
       lastTaskEnd+=portion;
       return nxt;
    }

    public void remove()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getHash()
    {
        return hash;
    }
    


}
