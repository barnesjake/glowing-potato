### Update commit without pushing another commit
```git add .
git commit --amend
git push --set-upstream origin <branch_name> -f
```


```
git rebase master -i
```
https://www.squash.io/how-to-rebase-local-branch-onto-remote-master-in-git/


### Delete old branches on local, be cautious
```
git branch --no-merged | grep -v \* | xargs git branch -D 
```


### Good aliases to set
```gcm -> git checkout master
gcb   -> git checkout -b
ggpush='git push origin "$(git_current_branch)"'
ggpull= ‘git pull origin "$(git_current_branch)"'
gl -> git pull
gst -> git status
ga -> git add
gpu='git push upstream'
'gca!'='git commit -v -a --amend'
gcam='git commit -a -m'
'gcan!'='git commit -v -a --no-edit --amend'
'gcans!'='git commit -v -a -s --no-edit --amend'
```
