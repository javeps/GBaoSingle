#!/bin/bash
#for I in 1 2 3 4 5 6 7 8
#do

  #telnet 127.0.0.1 21081
  /usr/bin/java -Xms256m -Xmx256m -Xmn64m -XX:MaxPermSize=64M -XX:+ForceTimeHighResolution -jar GBOGate.jar &

#done
