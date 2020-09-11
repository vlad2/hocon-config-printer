#!/bin/bash -e

echo "This will install executables in ~/bin. Press CTRL-C now to exit."
sleep 3

mvn package

mkdir -p ~/bin

cp -p hocon-config-printer ~/bin
cp -p ./target/hocon-config-printer-*jar-with-dependencies.jar ~/bin

echo 'All ok, now you can call "hocon-config-printer" from anywhere (assuming ~/bin is in $PATH)'
