import os
import subprocess

craftbukkit_source = "./src/main/java/net/minecraft"
nms_source = "./decompiled/net/minecraft"

for root, dirs, files in os.walk(craftbukkit_source):
    for file_name in files:
        relative_path = str(os.path.join(root, file_name)).replace(craftbukkit_source, "").replace("\\", "/")
        diff_process = subprocess.Popen("diff -u --label a/net/minecraft%s \"%s\" --label b/net/minecraft%s \"%s\"" % 
            (relative_path, os.path.join(root, file_name).replace(craftbukkit_source, nms_source), relative_path, os.path.join(root, file_name)),
                stdout=subprocess.PIPE,
                stderr=subprocess.PIPE,
                shell=True,
                universal_newlines=True)
        stdout, stderr = diff_process.communicate()

        if (stderr):
            print(stderr)
            exit()

        if not (stdout):
            continue  # no changes to the given java file, don't make a patch file
            
        patch_output = open("./nms-patches/%s" % file_name.replace(".java", ".patch"), 'w')
        patch_output.write(stdout)
        patch_output.close()

        print("🔨 New patch: %s." % file_name.replace(".java", ".patch"))
