return [
	isDefault : { false },
	register : { cli -> cli.upgrade('Upgrade O(t) command line tools') },
	supports : { o -> o.upgrade },
	exec : {
		out('Executing upgrade ...')
		sh('git', 'pull')
	}
]