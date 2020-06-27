#!/bin/bash

if [ -z "$1" ]
then
    echo "Please run this script again with the clean decompile sources as an argument. In most cases this will be ./decompiled"
    exit
fi

# https://stackoverflow.com/a/38595160
# https://stackoverflow.com/a/800644
if sed --version >/dev/null 2>&1; then
  strip_cr() {
    sed -i -- "s/\r//" "$@"
  }
else
  strip_cr () {
    sed -i "" "s/$(printf '\r')//" "$@"
  }
fi

# 1=${1%"/"} # remove trailing '/' if present

src="src/main/java/net/minecraft/"
minecraftSource="$1/net/minecraft/"

rm -rf "$src" # remove existing nms source from /src/main/java
mkdir -p "$src" # make sure the nms folder exists

for patchFile in $(/bin/find "nms-patches" -name '*.patch')
do
  patchFileClean=${patchFile#"nms-patches/"}
  file="$(echo $patchFileClean | cut -d. -f1).java"

  if [ -f "$minecraftSource$file" ]
  then
    echo "Patching $file < $patchFileClean"
    strip_cr "$minecraftSource$file"
    mkdir -p "$(dirname "$src$file")"
    cp "$minecraftSource$file" "$src$file"
    patch -d "src/main/java/" "net/minecraft/$file" < "$patchFile" > /dev/null
  else
    echo "Unable to apply $patchFileClean."
    echo "  $file not found."
  fi

done
