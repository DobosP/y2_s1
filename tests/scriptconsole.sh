echo "--- start update ---"
sudo apt-get update
echo "--- end update ---"

echo "--- apt install zsh tmux python-pip ---"
sudo apt-get install zsh tmux python-pip
echo "--- end apt install ---"

echo "--- install oh-my-zsh ---"
sh -c "$(wget https://raw.githubusercontent.com/robbyrussell/oh-my-zsh/master/tools/install.sh -O -)"
echo "--- end install ---"

echo "--- install powerline ---"
su -c 'pip install git+git://github.com/Lokaltog/powerline'
echo "--- end install ---"

echo "--- install powerline font ---"
wget https://github.com/Lokaltog/powerline/raw/develop/font/PowerlineSymbols.otf https://github.com/Lokaltog/powerline/raw/develop/font/10-powerline-symbols.conf
sudo mv PowerlineSymbols.otf /usr/share/fonts/
sudo fc-cache -vf
sudo mv 10-powerline-symbols.conf /etc/fonts/conf.d/
echo "--- end install powerline font ---"

echo "--- append text to vimrc ---"
sudo touch /etc/vim/vimrc
sudo cat <<EOT>> /etc/vim/vimrc
" for powerline
set rtp+=/usr/local/lib/python2.7/dist-packages/powerline/bindings/vim/
" Always show statusline
set laststatus=2
" Use 256 colours (Use this setting only if your terminal supports 256 colours)
set t_Co=256"
EOT
echo "--- finished ---"
echo "--- append text to zshrc ---"
sudo touch /etc/zsh/zshrc
sudo cat <<EOT>> /etc/zsh/zshrc
# for powerline
if [[ -r /usr/local/lib/python2.7/dist-packages/powerline/bindings/zsh/powerline.zsh ]]; then
    source /usr/local/lib/python2.7/dist-packages/powerline/bindings/zsh/powerline.zsh
fi
export TERM=xterm-256color
EOT
echo "--- finished ---"
echo "--- append text to .tmux.conf ---"
sudo touch ~/.tmux.conf
sudo cat <<EOT>> ~/.tmux.conf
source /usr/local/lib/python2.7/dist-packages/powerline/bindings/tmux/powerline.conf
set-option -g default-terminal "screen-256color"
set-option -g default-shell /bin/zsh
EOT
echo "--- finished ---"
