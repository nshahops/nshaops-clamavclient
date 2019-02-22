//Author: nshaops

//Example: To scan a sample document
//> groovy clamAV_tester.groovy localhost 3310 C:\\nshaops\\test\\directory\\doc.pdf


import java.nio.ByteBuffer
import java.nio.ByteOrder


if (args.length != 3) {
    println 'usage: groovy waitForPort <host> <port> <timeout seconds>'
    System.exit 1
}

String host = args[0]
int port = args[1] as Integer
String fileName = args[2]
long timeout = 3000

File file = new File(fileName)

long start = System.currentTimeMillis()
boolean connected = false

//Type conversion to 32 bit big endian byte array
public static byte[] toArray(int value){
    ByteBuffer buffer = ByteBuffer.allocate(4);
    buffer.order(ByteOrder.BIG_ENDIAN);
    buffer.putInt(value);
    buffer.flip();
    return buffer.array();
}

while (!connected && System.currentTimeMillis() - start < timeout) {
    try {
        s = new Socket(host, port)
        connected = true
        s << "zINSTREAM\0"
        s << toArray(file.size().toInteger())
        s << file.getText()
        s << toArray(0)
        
        s.withStreams { inStream, outStream -> 
            def reader = inStream.newReader() 
            def dataOut = reader.readLine() 
            println dataOut
        }
      
    } catch (e) {
        println "Waiting for $host:$port... ${e.message}"
        sleep 2000
    }
}

if (!connected) {
    println "Port not available: $host:$port"
    System.exit 1
}
else {
    println "Port available: $host:$port"
}