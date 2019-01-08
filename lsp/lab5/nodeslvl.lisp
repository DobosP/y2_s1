; L2 2

(defun treelvlk(tree k lvl stack)
  (cond
    ((NULL tree) (list))
    (
      (and (equal 2 (cadr tree)) (equal lvl k))
      (cons (car tree) (treelvlk (nthcdr 2 tree) k (+ 1 lvl) (cons (+ 1 lvl) stack)))
    )
    (
      (equal 2 (cadr tree))
      (treelvlk (nthcdr 2 tree) k (+ 1 lvl) (cons (+ 1 lvl) stack))
    )
    (
      (and (equal 1 (cadr tree)) (equal lvl k))
      (cons (car tree) (treelvlk (nthcdr 2 tree) k (+ 1 lvl) stack))
    )
    (
      (equal 1 (cadr tree))
      (treelvlk (nthcdr 2 tree) k (+ 1 lvl) stack)
    )
    (
      (and (equal 0 (cadr tree)) (equal lvl k))
      (cons (car tree) (treelvlk (nthcdr 2 tree) k (car stack) (cdr stack)))
    )
    (
      (equal 0 (cadr tree))
      (treelvlk (nthcdr 2 tree) k (car stack) (cdr stack))
    )
  )
)

(defun klvltree(tree k)
    (treelvlk tree k 1 (list))
)
