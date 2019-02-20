# Raj's Jar Files

## Important Notes for all contributors
* When you clone the project, make sure to open the **android** folder with android studio and not the top level folder.
* Make changes on your **own branch** and then **merge** it.

## How to work on your own branches
```
# IF YOU WANT FRESH REPO 
git clone <remote url>
# OR IF YOU ALREADY CLONED
git checkout master
git pull origin master

# WORK ON YOUR OWN THING
git checkout -b <your branch> (create & checkout in one go)

# ~~~~~~~ WORK ~~~~~~~~~

git add <files>
git commit -m "commit message"

# WHEN YOU ARE READY TO MERGE 
git checkout master
git pull origin master (to make sure local master is up-to-date)
git merge <your branch> (merge local/<your branch> to local/master)
git push -u origin master

# DONE
```


