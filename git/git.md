### Update commit without pushing another commit
git add .
git commit --amend
git push --set-upstream origin <branch_name> -f

git rebase master -i

### Good aliases to set
gcm -> git checkout master
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
