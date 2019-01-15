; L3 5

(defun sumlist(L)
  (cond
  ((and (Integerp L) (= (mod L 2) 0)) L)
  ((and (Integerp L) (= (mod L 2) 1)) (* L -1))
  ((listp L) (apply '+(mapcar 'sumlist L)))
  (T 0)
  )
)
